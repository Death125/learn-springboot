package com.learnspringboot.models;

public record Weapon(String name, Long power) {

    public Weapon(String name, Long power) {
        this.name = name;
        this.power = power;
    }

    public Weapon() {
        this("Unbeatable", 99999999999999999L);
    }

    public static Weapon weapon1 = new Weapon("Murasame", 333L);

    public String name() {
        return name;
    }

    public Long power() {
        return power;
    }

}
