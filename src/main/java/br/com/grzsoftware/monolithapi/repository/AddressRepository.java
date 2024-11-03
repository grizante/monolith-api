package br.com.grzsoftware.monolithapi.repository;

import br.com.grzsoftware.monolithapi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
