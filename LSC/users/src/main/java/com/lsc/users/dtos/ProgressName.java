package com.lsc.users.dtos;

public enum ProgressName {
    BASICO("Básico"),
    INTERMEDIO("Intermedio"),
    AVANZADO("Avanzado");

    private String level;

    ProgressName(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}
