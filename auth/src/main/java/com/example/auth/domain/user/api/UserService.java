package com.example.auth.domain.user.api;

import com.example.auth.domain.user.impl.User;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto user);
    UserDto getUser(String userId);
    UserDto updateUser(UserDto user, String userId);
    void deleteUser(String userId);

    List<UserDto> getAllUsers();
}
