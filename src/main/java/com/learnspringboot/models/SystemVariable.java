package com.learnspringboot.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public record SystemVariable(
        @Value("${java.home}") String javaHome,
        @Value("${TEMP}") String tempDir,
        @Value("${email}") String email) {

    public String javaHome() {
        return javaHome;
    }

    public String tempDir() {
        return tempDir;
    }

    public String email() {
        return email;
    }
}
