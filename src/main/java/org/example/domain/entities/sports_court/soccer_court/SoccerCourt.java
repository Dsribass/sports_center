package org.example.domain.entities.sports_court.soccer_court;

import org.example.domain.entities.sports_court.CourtSize;
import org.example.domain.entities.sports_court.SportsCourt;

import java.util.Objects;

public class SoccerCourt extends SportsCourt {
    private TypeOfSoccer typeOfSoccer;

    public SoccerCourt(Integer id, Boolean indoorCourt, CourtSize courtSize, TypeOfSoccer typeOfSoccer) {
        super(id, indoorCourt, courtSize);
        this.typeOfSoccer = typeOfSoccer;
    }

    public SoccerCourt() {
    }

    public TypeOfSoccer getTypeOfSoccer() {
        return typeOfSoccer;
    }

    public void setTypeOfSoccer(TypeOfSoccer typeOfSoccer) {
        this.typeOfSoccer = typeOfSoccer;
    }

    @Override
    public Integer getCapacity() {
        if(typeOfSoccer == null)
            throw new IllegalArgumentException("TypeOfSoccer can not be null");
        switch (typeOfSoccer){
            case FIELD: return 22;
            case FUTSAL: return 10;
            case SOCIETY: return 14;
            default: return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SoccerCourt that = (SoccerCourt) o;
        return typeOfSoccer == that.typeOfSoccer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), typeOfSoccer);
    }

    @Override
    public String toString() {
        return "SoccerCourt{" +
                "typeOfSoccer=" + typeOfSoccer +
                "} " + super.toString();
    }
}
