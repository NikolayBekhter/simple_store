package ru.nikbekhter.simple.store.auth.converters;

import org.springframework.stereotype.Component;
import ru.nikbekhter.simple.store.api.UserDto;
import ru.nikbekhter.simple.store.auth.entities.User;

@Component
public class UserConverter {

    //из user в dto
    public UserDto entityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setBalance(user.getBalance());
        userDto.setActive(user.isActive());
        return userDto;
    }
    //из dto в user
    public User dtoToEntity(UserDto userDto){
        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .username(userDto.getUsername())
                .balance(userDto.getBalance())
                .build();
    }
}
