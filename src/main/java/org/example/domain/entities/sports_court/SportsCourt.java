package org.example.domain.entities.sports_court;

import java.util.Objects;

public abstract class SportsCourt {
    private Integer id;
    private Boolean indoorCourt;
    private CourtSize courtSize;
    private Double courtValue;

    public abstract Integer getCapacity();

    public SportsCourt(Integer id, Boolean indoorCourt, CourtSize courtSize) {
        this.id = id;
        this.indoorCourt = indoorCourt;
        this.courtSize = courtSize;
    }

    public SportsCourt() {
    }

    public Integer getId() {
        return id;
    }

    public Boolean getIndoorCourt() {
        return indoorCourt;
    }

    public void setIndoorCourt(Boolean indoorCourt) {
        this.indoorCourt = indoorCourt;
    }

    public CourtSize getCourtSize() {
        return courtSize;
    }

    public void setCourtSize(CourtSize courtSize) {
        this.courtSize = courtSize;
    }

    public void setCourtValue(Double courtValue) {
        this.courtValue = courtValue;
    }

    public Double getCourtValue() {
        return courtValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsCourt that = (SportsCourt) o;
        return Objects.equals(id, that.id) && indoorCourt.equals(that.indoorCourt) && courtSize == that.courtSize && courtValue.equals(that.courtValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, indoorCourt, courtSize, courtValue);
    }

    @Override
    public String toString() {
        return "SportsCourt{" +
                "id=" + id +
                ", indoorCourt=" + indoorCourt +
                ", courtSize=" + courtSize +
                ", courtValue=" + courtValue +
                '}';
    }
}
