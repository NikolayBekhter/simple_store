package ru.nikbekhter.simple.store.auth.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.nikbekhter.simple.store.auth.entities.Role;
import ru.nikbekhter.simple.store.auth.services.UserService;
import ru.nikbekhter.simple.store.auth.converters.UserConverter;
import ru.nikbekhter.simple.store.auth.entities.User;
import ru.nikbekhter.simple.store.auth.mail.MyMailSender;
import ru.nikbekhter.simple.store.auth.utils.JwtTokenUtil;
import ru.nikbekhter.simple.store.auth.api.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/v1")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final UserConverter userConverter;
    private final MyMailSender myMailSender;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest request) {
        log.info("Auth request: [{}, {}]", request.getUsername(), request.getPassword());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        if (userService.findByEmail(userDto.getEmail()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с таким именем уже существует"), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(userService.save(userDto));
    }

    @GetMapping("/users/{userId}")
    public UserDto findById(@PathVariable Long userId) {
        return userConverter.entityToDto(userService.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с id: " + userId + " не найден!")));
    }

    @GetMapping("/users/is_active/{username}")
    public UserDto isActiveForUser (@PathVariable String username) {
        if (username != null) {
            User user = userService.findByEmail(username)
                    .orElseThrow(() -> new ResourceNotFoundException("Пользователь " + username + " не найден!"));
            return userConverter.entityToDto(user);
        } else
            return null;
    }

    @PostMapping("/users/set_role")
    public UserDto setRole(@RequestBody RoleRequest roleRequest) {
        return userConverter.entityToDto(userService.setRole(roleRequest.getUsername(), roleRequest.getRole()));
    }

    @GetMapping("/users/get_roles/{username}")
    public List<Role> getRoles(@PathVariable String username) {
        return userService.getUserRoles(username);
    }

}
