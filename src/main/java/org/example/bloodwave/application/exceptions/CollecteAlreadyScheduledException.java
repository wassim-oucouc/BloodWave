package org.example.bloodwave.application.exceptions;

public class CollecteAlreadyScheduledException extends RuntimeException{

    public CollecteAlreadyScheduledException(String message)
    {
        super(message);
    }
}
