package com.learnspringboot.enums;

public enum LegendaryHeroEnum {
    Zanos(980),
    Riberia(1000),
    Grados(999);

    private final int value;

    LegendaryHeroEnum(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

};
