package org.example.domain.entities.sports_court_rental;

import org.example.domain.entities.client.Client;
import org.example.domain.entities.sports_court.SportsCourt;

import java.time.LocalDate;

public class SportsCourtRental {
    private Integer id;
    private SportsCourt sportCourt;
    private Client client;
    private LocalDate date;

    public SportsCourtRental(Integer id, SportsCourt sportCourt, Client client, LocalDate date) {
        this.id = id;
        this.sportCourt = sportCourt;
        this.client = client;
        this.date = date;
    }

    public SportsCourtRental(SportsCourt sportCourt, Client client, LocalDate date) {
        this.sportCourt = sportCourt;
        this.client = client;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
