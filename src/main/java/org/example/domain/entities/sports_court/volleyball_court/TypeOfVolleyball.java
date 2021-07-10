package org.example.domain.entities.sports_court.volleyball_court;

import org.example.domain.entities.sports_court.CourtSize;
import org.example.domain.entities.sports_court.soccer_court.TypeOfSoccer;

import java.util.Arrays;

public enum TypeOfVolleyball{
    BEACH("Areia"),
    COURT("Quadra");

    private String label;

    TypeOfVolleyball(String label) {
        this.label = label;
    }

    public static TypeOfVolleyball convertToEnum(String value){
        return Arrays.stream(TypeOfVolleyball.values())
                .filter(type -> type.toString().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public String toString() {
        return label;
    }
}
