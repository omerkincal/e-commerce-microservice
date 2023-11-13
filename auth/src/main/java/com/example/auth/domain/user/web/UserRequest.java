package com.example.auth.domain.user.web;

import com.example.auth.domain.user.api.UserDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class UserRequest {

    @NotBlank(message = "The name cannot be empty!")
    private String name;

    @NotBlank(message = "The lastname cannot be empty!")
    private String lastname;

    @NotBlank(message = "The email cannot be empty")
    @Email(message = "There is something wrong with mail's format!")
    private String email;

    @NotBlank(message = "The password cannot be empty!")
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
