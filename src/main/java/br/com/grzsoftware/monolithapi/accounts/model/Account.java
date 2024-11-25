package br.com.grzsoftware.monolithapi.accounts.model;

import br.com.grzsoftware.monolithapi.clients.model.Client;
import br.com.grzsoftware.monolithapi.roles.model.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String password;

    private boolean enabled;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Client> clients;

    @ManyToMany(mappedBy = "role")
    @JsonManagedReference
    private List<Role> roles;

    public Account() {
    }

    public Account(String name, String email, String password, List<Client> clients, List<Role> roles, boolean enabled) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.clients = clients;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
