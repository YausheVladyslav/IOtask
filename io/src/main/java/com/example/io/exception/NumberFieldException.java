package com.example.io.exception;

public class NumberFieldException extends RuntimeException {

    public NumberFieldException() {
        super("Wrong amount of field");
    }
}
