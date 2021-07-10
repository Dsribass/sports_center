package org.example.domain.entities.sports_court;

import java.util.Arrays;

public enum CourtSize {
    BIG("Grande"),
    MEDIUM("Média"),
    SMALL("Pequena");

    private String label;

    CourtSize(String label) {
        this.label = label;
    }

    public static CourtSize convertToEnum(String value){
        return Arrays.stream(CourtSize.values())
                .filter(courtSize -> courtSize.toString().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public String toString() {
        return label;
    }
}
