package br.com.grzsoftware.monolithapi.repository;

import br.com.grzsoftware.monolithapi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findAddressByCityAndStreetAndState(String city, String street, String state);
}
