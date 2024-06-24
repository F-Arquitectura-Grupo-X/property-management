package com.rentstate.property_management.infrastructure.repository;

import com.rentstate.property_management.domain.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Boolean existsByOwnerId(Long ownerId);
    List<Client> findAllByOwnerId(Long ownerId);
}
