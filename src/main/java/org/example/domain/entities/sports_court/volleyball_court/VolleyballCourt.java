package org.example.domain.entities.sports_court.volleyball_court;

import org.example.domain.entities.sports_court.CourtSize;
import org.example.domain.entities.sports_court.SportsCourt;

import java.util.Objects;

public class VolleyballCourt extends SportsCourt {
    private TypeOfVolleyball typeOfVolleyball;

    public VolleyballCourt(Integer id, Boolean indoorCourt, CourtSize courtSize, TypeOfVolleyball typeOfVolleyball) {
        super(id, indoorCourt, courtSize);
        this.typeOfVolleyball = typeOfVolleyball;
    }

    public VolleyballCourt() {
    }

    public TypeOfVolleyball getTypeOfVolleyball() {
        return typeOfVolleyball;
    }

    public void setTypeOfVolleyball(TypeOfVolleyball typeOfVolleyball) {
        this.typeOfVolleyball = typeOfVolleyball;
    }

    @Override
    public Integer getCapacity() {
        if(typeOfVolleyball == null)
            throw new IllegalArgumentException("TypeOfVolleyball can not be null");
        switch (typeOfVolleyball){
            case COURT: return 12;
            case BEACH: return 4;
            default: return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VolleyballCourt that = (VolleyballCourt) o;
        return typeOfVolleyball == that.typeOfVolleyball;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), typeOfVolleyball);
    }

    @Override
    public String toString() {
        return "VolleyballCourt{" +
                "typeOfVolleyball=" + typeOfVolleyball +
                "} " + super.toString();
    }
}
