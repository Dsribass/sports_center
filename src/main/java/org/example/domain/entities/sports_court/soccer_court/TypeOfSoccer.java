package org.example.domain.entities.sports_court.soccer_court;

import org.example.domain.entities.sports_court.volleyball_court.TypeOfVolleyball;

import java.util.Arrays;

public enum TypeOfSoccer {
    SOCIETY("Society"),
    FIELD("Campo"),
    FUTSAL("Futsal");

    private String label;

    TypeOfSoccer(String label) {
        this.label = label;
    }

    public static TypeOfSoccer convertToEnum(String value){
        return Arrays.stream(TypeOfSoccer.values())
                .filter(type -> type.toString().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public String toString() {
        return label;
    }
}
