package br.com.grzsoftware.monolithapi.model;

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
    private List<Client> client;

    public Address() {}

    public Address(String street, String city, String state, List<Client> client) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Client> getClient() {
        return client;
    }

    public void setClient(List<Client> client) {
        this.client = client;
    }
}
