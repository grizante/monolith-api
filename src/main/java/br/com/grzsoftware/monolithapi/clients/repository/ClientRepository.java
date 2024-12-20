package br.com.grzsoftware.monolithapi.clients.repository;

import br.com.grzsoftware.monolithapi.clients.model.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {
    Client save(Client client);

    Optional<Client> findById(Long id);

    Optional<Client> findByEmail(String email);

    void deleteById(Long id);
}
