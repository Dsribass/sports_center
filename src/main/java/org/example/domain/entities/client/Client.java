package org.example.domain.entities.client;

import org.example.domain.entities.sports_court_rental.SportsCourtRental;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Client {
    private String cpf;
    private String name;
    private List<SportsCourtRental> sportsCourtRentals = new ArrayList<>();

    public Client(String cpf, String name, List<SportsCourtRental> courtRentals) {
        this.cpf = cpf;
        this.name = name;
        this.sportsCourtRentals = courtRentals;
    }

    public Client(String cpf, String name) {
        this(cpf,name,null);
    }

    public Client() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Iterator<SportsCourtRental> getSportsCourtRentals() {
        return sportsCourtRentals.iterator();
    }

    public void setSportsCourtRentals(List<SportsCourtRental> sportsCourtRentals) {
        this.sportsCourtRentals = sportsCourtRentals;
    }

    public void addSportsCourtRental(SportsCourtRental sportsCourtRental) {
        if(sportsCourtRental == null) throw new IllegalArgumentException("CourtRental can not be null");
        sportsCourtRentals.add(sportsCourtRental);
    }

    public boolean removeSportsCourtRental(SportsCourtRental sportsCourtRental) {
        if(sportsCourtRental == null)
            throw new IllegalArgumentException("CourtRental can not be null");
        return sportsCourtRentals.remove(sportsCourtRental);
    }
}
