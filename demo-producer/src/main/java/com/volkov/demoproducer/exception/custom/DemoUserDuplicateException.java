package com.volkov.demoproducer.exception.custom;

public class DemoUserDuplicateException extends RuntimeException {
    public DemoUserDuplicateException(){
        super("DemoUser indexed parameters already exist in the data base");
    }
}
