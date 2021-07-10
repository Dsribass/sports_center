package org.example.domain.entities.client;

import java.util.Objects;

public class Address {
    private String clientCPF;
    private String state;
    private String city;
    private String road;
    private Integer number;
    private String complement;

    public Address(String clientCPF, String state, String city, String road, Integer number, String complement) {
        this.clientCPF = clientCPF;
        this.state = state;
        this.city = city;
        this.road = road;
        this.number = number;
        this.complement = complement;
    }

    public Address(String clientCPF) {
        this.clientCPF = clientCPF;
    }

    public String getClientCPF() {
        return clientCPF;
    }

    public void setClientCPF(String clientCPF) {
        this.clientCPF = clientCPF;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return clientCPF.equals(address.clientCPF) && state.equals(address.state) && city.equals(address.city) && road.equals(address.road) && number.equals(address.number) && complement.equals(address.complement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientCPF, state, city, road, number, complement);
    }

    @Override
    public String toString() {
        return "Address{" +
                "state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", road='" + road + '\'' +
                ", number=" + number +
                ", complement='" + complement + '\'' +
                '}';
    }
}
