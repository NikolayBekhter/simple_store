package ru.nikbekhter.simple.store.auth.converters;

import org.springframework.stereotype.Component;
import ru.nikbekhter.simple.store.auth.api.UserDto;
import ru.nikbekhter.simple.store.auth.entities.User;

@Component
public class UserConverter {

    //из user в dto
    public UserDto entityToDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .balance(user.getBalance())
                .active(user.isActive())
                .build();
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
