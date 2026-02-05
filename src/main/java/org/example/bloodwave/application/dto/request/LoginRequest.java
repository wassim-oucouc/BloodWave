package org.example.bloodwave.application.dto.request;


import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
}
