package com.example.auth.domain.user.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private long userId;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private int role;
}
