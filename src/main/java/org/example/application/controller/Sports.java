package org.example.application.controller;

public enum Sports {
    SOCCER("Futebol"),
    VOLLEYBALL("Vôlei");
    private String label;

    Sports(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
