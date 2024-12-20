package br.com.grzsoftware.monolithapi.clients.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String street;

    private String city;

    private String state;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Client> clients;

    public Address() {
    }

    public Address(String street, String city, String state, List<Client> clients) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.clients = clients;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
