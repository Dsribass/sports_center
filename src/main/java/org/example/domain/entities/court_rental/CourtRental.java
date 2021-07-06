package org.example.domain.entities.court_rental;

import org.example.domain.entities.sports_court.SportsCourt;

import java.time.LocalDate;

public class CourtRental {
    private Integer id;
    private SportsCourt sportCourt;
    private LocalDate date;

    public CourtRental(Integer id, SportsCourt sportCourt, LocalDate date) {
        this.id = id;
        this.sportCourt = sportCourt;
        this.date = date;
    }

    public CourtRental(SportsCourt sportCourt, LocalDate date) {
        this.sportCourt = sportCourt;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public SportsCourt getSportCourt() {
        return sportCourt;
    }

    public void setSportCourt(SportsCourt sportCourt) {
        this.sportCourt = sportCourt;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
