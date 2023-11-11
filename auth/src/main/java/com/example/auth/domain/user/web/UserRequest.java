package com.example.auth.domain.user.web;

import com.example.auth.domain.user.api.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    private String name;
    private String lastname;
    private String email;
    private String password;


    // controller'dan service giden requesti dto'ya çevirme işlemi
    public UserDto toDto(){
        return UserDto.builder()
                .name(this.name)
                .lastname(this.lastname)
                .email(this.email)
                .password(this.password)
                .build();
    }
}
