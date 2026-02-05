package org.example.bloodwave.application.exceptions;

public class DonneurNotFoundException extends RuntimeException{

    public DonneurNotFoundException(String message)
    {
        super(message);
    }
}
