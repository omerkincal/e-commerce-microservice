package com.example.basket.domain.basket.api.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long userId;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private int role;
}
