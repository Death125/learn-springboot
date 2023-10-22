package com.learnspringboot.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import jakarta.persistence.Transient;

@Component
public class Dragon {
    @Autowired
    private Environment environment;

    @Value("${dragon.name}")
    private String name;

    @Value("${dragon.power}")
    private int power;

    @Transient
    private String rarity;

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public String getRarity() {
        return environment.getProperty("dragon.rarity");
    }
}
