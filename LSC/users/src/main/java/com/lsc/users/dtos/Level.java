package com.lsc.users.dtos;

public enum Level {
    BASICO("Basico"),
    INTERMEDIO("Intermedio"),
    AVANZADO("Avanzado");

    private String level;

    Level(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}
