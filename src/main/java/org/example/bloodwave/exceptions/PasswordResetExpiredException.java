package org.example.bloodwave.exceptions;

public class PasswordResetExpiredException extends RuntimeException{

    public PasswordResetExpiredException(String message)
    {
        super(message);
    }
}
