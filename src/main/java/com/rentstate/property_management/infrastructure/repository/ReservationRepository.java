package com.rentstate.property_management.infrastructure.repository;

import com.rentstate.property_management.domain.model.entities.Property;
import com.rentstate.property_management.domain.model.entities.Reservation;
import com.rentstate.property_management.domain.model.valueObjects.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Boolean existsByOwnerId (Long ownerId);
    List<Reservation> findAllByPropertyAndStatus (Property property, ReservationStatus reservationStatus);
    List<Reservation> findAllByOwnerId(Long ownerId);
}
