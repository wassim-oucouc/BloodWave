package org.example.bloodwave.application.exceptions;

public class PasswordResetExpiredException extends RuntimeException{

    public PasswordResetExpiredException(String message)
    {
        super(message);
    }
}
