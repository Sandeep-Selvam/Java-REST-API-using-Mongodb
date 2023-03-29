package com.myrestapi.restapi;

public class ServerNotFoundException extends RuntimeException{

    public  ServerNotFoundException(String message) {
        super(message);
    }
}
