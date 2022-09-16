package com.example.io.exception;

public class FileEmptyException extends RuntimeException{

    public FileEmptyException(){
        super("File is empty");
    }
}
