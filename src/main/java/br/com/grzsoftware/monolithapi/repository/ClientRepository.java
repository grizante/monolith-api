package br.com.grzsoftware.monolithapi.repository;

import br.com.grzsoftware.monolithapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {
    Client save(Client client);
}
