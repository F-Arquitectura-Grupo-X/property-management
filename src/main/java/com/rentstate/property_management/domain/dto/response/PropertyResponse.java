package com.rentstate.property_management.domain.dto.response;

import com.rentstate.property_management.domain.model.entities.Property;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PropertyResponse {


    private Long id;
    private String name;
    private String description;
    private String characteristics;
    private String location;
    private String category;
    private Boolean available;
    private Boolean posted;
    private String urlImg;
    private Long ownerId;

    public PropertyResponse(Property property) {
        id = property.getId();
        name = property.getName();
        description = property.getDescription();
        characteristics = property.getCharacteristics();
        location = property.getLocation();
        category = property.getCategory().toString();
        available = property.getAvailable();
        posted = property.getPosted();
        urlImg = property.getUrlImg();
        ownerId = property.getOwnerId();

    }
}
