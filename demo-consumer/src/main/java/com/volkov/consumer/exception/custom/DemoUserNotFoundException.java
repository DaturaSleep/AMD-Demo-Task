package com.volkov.consumer.exception.custom;

public class DemoUserNotFoundException extends RuntimeException{
    public DemoUserNotFoundException(){
        super("Demo User not found");
    }
}
