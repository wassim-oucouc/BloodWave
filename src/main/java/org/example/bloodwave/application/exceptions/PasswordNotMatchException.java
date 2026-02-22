package org.example.bloodwave.application.exceptions;

public class PasswordNotMatchException extends RuntimeException{

    public PasswordNotMatchException(String message)
    {
        super(message);
    }
}
