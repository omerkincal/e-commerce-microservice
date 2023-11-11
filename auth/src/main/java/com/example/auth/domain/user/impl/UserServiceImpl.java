package com.example.auth.domain.user.impl;

import com.example.auth.domain.user.api.UserDto;
import com.example.auth.domain.user.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public static final int STATUS_CUSTOMER = 0;
    public static final int STATUS_ADMIN = 1;
    public static final int STATUS_SUPER_ADMIN = 2;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = toEntity(userDto);
        if(user.getName().equals("admin")){
            user.setRole(STATUS_ADMIN);
        }else if (user.getName().equals("super-admin")){
            user.setRole(STATUS_SUPER_ADMIN);
        }else {
            user.setRole(STATUS_CUSTOMER);
        }
        user = repository.save(user);
        return toDto(user);
    }

    @Override
    public UserDto getUser(String userId) {
        User user = repository.findById(Long.valueOf(userId)).get();
        return toDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = repository.findById(Long.valueOf(userId)).get();
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user = repository.save(user);
        return toDto(user);
    }

    @Override
    public void deleteUser(String userId) {
        User user =  repository.findById(Long.valueOf(userId)).get();
        repository.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(user -> toDto(user))
                .collect(Collectors.toList());
    }

    public UserDto toDto(User user){
        return UserDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    public User toEntity(UserDto user){
        User entity = new User();
        entity.setName(user.getName());
        entity.setLastname(user.getLastname());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        return entity;
    }
}
