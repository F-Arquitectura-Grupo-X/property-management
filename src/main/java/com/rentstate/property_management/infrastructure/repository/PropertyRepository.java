package com.rentstate.property_management.infrastructure.repository;

import com.rentstate.property_management.domain.model.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property,Long> {

    List<Property> findAllByOwnerId(Long id);
    Boolean existsByOwnerId(Long id);

}
