package com.learnspringboot.exception;

public class HeroNotFoundException extends Exception {
    public HeroNotFoundException(String message) {
        super(message);
    }
}
