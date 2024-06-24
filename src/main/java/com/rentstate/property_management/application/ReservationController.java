package com.rentstate.property_management.application;

import com.rentstate.property_management.domain.dto.request.ReservationRequest;
import com.rentstate.property_management.domain.dto.response.ReservationResponse;
import com.rentstate.property_management.domain.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v17/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<String> addReservation(@RequestBody ReservationRequest reservationRequest){
        if(reservationService.addReservation(reservationRequest)){
            return new ResponseEntity<>("created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/accept/{reservationId}")
    public ResponseEntity<String> acceptReservation(@PathVariable Long reservationId){
        if(reservationService.acceptReservation(reservationId)){
            return new ResponseEntity<>("accepted", HttpStatus.OK);
        }
        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<ReservationResponse>> getReservationsByOwnerId(@PathVariable Long ownerId){
        List<ReservationResponse> reservationResponseList = reservationService.getReservationsByOwnerId(ownerId);

        if(reservationResponseList == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(reservationResponseList, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{reservationId}")
    public ResponseEntity<String>deleteReservation(@PathVariable Long reservationId){
        if(reservationService.delete(reservationId)){
            return new ResponseEntity<>("deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }
}
