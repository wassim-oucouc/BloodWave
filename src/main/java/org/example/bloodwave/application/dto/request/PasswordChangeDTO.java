package org.example.bloodwave.application.dto.request;

import lombok.Data;

@Data
public class PasswordChangeDTO {
    private String oldPassword;
    private String newPassword;
}