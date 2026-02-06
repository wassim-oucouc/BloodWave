package org.example.bloodwave.application.exceptions;

public class DonNotFoundException extends RuntimeException{

    public DonNotFoundException(String message)
    {
        super(message);
    }
}
