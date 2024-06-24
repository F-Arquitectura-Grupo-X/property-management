package com.rentstate.property_management.domain.service.impl;

import com.rentstate.property_management.domain.dto.request.ReservationRequest;
import com.rentstate.property_management.domain.dto.response.ReservationResponse;
import com.rentstate.property_management.domain.model.entities.Client;
import com.rentstate.property_management.domain.model.entities.Property;
import com.rentstate.property_management.domain.model.entities.Reservation;
import com.rentstate.property_management.domain.model.valueObjects.ReservationStatus;
import com.rentstate.property_management.domain.service.ReservationService;
import com.rentstate.property_management.infrastructure.repository.ClientRepository;
import com.rentstate.property_management.infrastructure.repository.PropertyRepository;
import com.rentstate.property_management.infrastructure.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final PropertyRepository propertyRepository;
    private final ClientRepository clientRepository;

    @Override
    public Boolean addReservation(ReservationRequest reservationRequest) {

        Optional<Property> property = propertyRepository.findById(reservationRequest.getPropertyId());
        if (property.isEmpty()) return false;

        //VALIDAR SI EL USUARIO EXISTE

        Property propertyEntity = property.get();

        if(isUserAlreadyReserved(propertyEntity, reservationRequest.getUserId())){
            return false;
        }


        Reservation reservation = new Reservation(reservationRequest, propertyEntity);
        reservationRepository.save(reservation);
        propertyEntity.getReservations().add(reservation);
        propertyRepository.save(propertyEntity);

        return true;
    }

    @Override
    public Boolean delete(Long reservationId) {

        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if (reservation.isEmpty()) return false;

        reservationRepository.delete(reservation.get());
        return true;
    }

    @Override
    public Boolean acceptReservation(Long reservationId) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if (reservation.isEmpty()) return false;

        Reservation reservationEntity = reservation.get();
        reservationEntity.setStatus(ReservationStatus.CONFIRMED);
        reservationRepository.save(reservationEntity);

        Client client = Client.builder()
                .ownerId(reservationEntity.getOwnerId())
                .userId(reservationEntity.getUserId())
                .clientName(reservationEntity.getUserName())
                .createdAt(new Date())
                .build();
        clientRepository.save(client);

        Property property = reservationEntity.getProperty();
        cancelReservations(property);
        property.setAvailable(false);
        propertyRepository.save(property);

        return true;
    }

    @Override
    public List<ReservationResponse> getReservationsByOwnerId(Long ownerId) {
        if(reservationRepository.existsByOwnerId(ownerId)) {
            List<Reservation> reservationList = reservationRepository.findAllByOwnerId(ownerId);
            return reservationList.stream().map(ReservationResponse::new).toList();
        }
        return null;
    }


    private void cancelReservations(Property property) {
        List<Reservation> reservationList =
                reservationRepository.findAllByPropertyAndStatus(property, ReservationStatus.PENDING);

        reservationList.forEach(reservation -> reservation.setStatus(ReservationStatus.CANCELLED));

        reservationRepository.saveAll(reservationList);
    }

    private boolean isUserAlreadyReserved(Property property, Long userId) {
        return property.getReservations().stream()
                .anyMatch(reservation -> reservation.getUserId().equals(userId));
    }
}
