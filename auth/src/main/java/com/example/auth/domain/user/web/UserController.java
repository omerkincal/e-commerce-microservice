package com.example.auth.domain.user.web;

import com.example.auth.domain.user.api.UserDto;
import com.example.auth.domain.user.api.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponse> saveUser(@Valid @RequestBody UserRequest user){
        UserDto dto = service.saveUser(user.toDto());
        return new ResponseEntity<>(toResponse(dto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUserDto(@PathVariable(name = "id") String userId){
        return new ResponseEntity<>(toResponse(service.getUser(userId)), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") String userId){
         service.deleteUser(userId);
         return new ResponseEntity<>("Succesfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return new ResponseEntity<>(service.getAllUsers()
                .stream()
                .map(userDto -> toResponse(userDto))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    public UserResponse toResponse(UserDto user){
        return UserResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

}
