package br.com.grzsoftware.monolithapi.repository;

import br.com.grzsoftware.monolithapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
