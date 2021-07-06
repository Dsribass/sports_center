package org.example.domain.entities.client;

import org.example.domain.entities.court_rental.CourtRental;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Client {
    private String cpf;
    private String name;
    private List<CourtRental> courtRentals = new ArrayList<>();

    public Client(String cpf, String name, List<CourtRental> courtRentals) {
        this.cpf = cpf;
        this.name = name;
        this.courtRentals = courtRentals;
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

    public Iterator<CourtRental> getCourtRentals() {
        return courtRentals.iterator();
    }

    public void addCourtRental(CourtRental courtRental) {
        if(courtRental == null) throw new IllegalArgumentException("CourtRental can not be null");
        courtRentals.add(courtRental);
    }
}
