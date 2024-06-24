package com.rentstate.property_management.application;

import com.rentstate.property_management.domain.dto.request.PropertyRequest;
import com.rentstate.property_management.domain.dto.response.PropertyResponse;
import com.rentstate.property_management.domain.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v17/property")
public class PropertyController {
    private final PropertyService propertyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PropertyResponse createProperty(@RequestBody PropertyRequest propertyRequest) {
        return propertyService.addProperty(propertyRequest);
    }

    @GetMapping({"/{propertyId}"})
    public ResponseEntity<PropertyResponse> getProperty(@PathVariable Long propertyId) {

        PropertyResponse propertyResponse = propertyService.getPropertyById(propertyId);

        if(propertyResponse == null) {
            return new ResponseEntity("Property with id " + propertyId + " not found",
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(propertyResponse,
                HttpStatus.OK);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<PropertyResponse>> getPropertiesByOwner(@PathVariable Long ownerId) {

        List<PropertyResponse> propertyResponseList = propertyService.getPropertiesByAuthorId(ownerId);
        if(propertyResponseList == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(propertyResponseList,HttpStatus.OK);
    }

    @PutMapping("/update/{propertyId}")
    public ResponseEntity<PropertyResponse> updateProperty(
            @PathVariable Long propertyId, @RequestBody PropertyRequest propertyRequest) {

        PropertyResponse propertyResponse = propertyService.updateProperty(propertyRequest,propertyId );
        if(propertyResponse == null) {
            return new ResponseEntity("Property with id " + propertyId + " not found",
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(propertyResponse,
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{propertyId}")
    public ResponseEntity<Boolean> deleteProperty(@PathVariable Long propertyId) {
        Boolean deleted = propertyService.deleteProperty(propertyId);
        if(!deleted) {
            return new ResponseEntity("Property with id " + propertyId + " not found",
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

}
