package br.com.grzsoftware.monolithapi.roles.repository;

import br.com.grzsoftware.monolithapi.roles.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
