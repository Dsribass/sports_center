package org.example.domain.entities.client;

import java.util.Objects;

public class Client {
    private String cpf;
    private String name;
    private Address address;

    public Client(String cpf, String name,Address address) {
        this.cpf = cpf;
        this.name = name;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getState() {
        return address.getState();
    }

    public String getCity() {
        return address.getCity();
    }

    public String getRoad() {
        return address.getRoad();
    }

    public Integer getNumber() {
        return address.getNumber();
    }

    public String getComplement() {
        return address.getComplement();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return cpf.equals(client.cpf) && name.equals(client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, name);
    }

    @Override
    public String toString() {
        return "Client{" +
                "cpf='" + cpf + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
