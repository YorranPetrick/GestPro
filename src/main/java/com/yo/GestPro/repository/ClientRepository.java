package com.yo.GestPro.repository;

import com.yo.GestPro.models.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    Optional<Client> findByLoginClient (String loginClient);
}
