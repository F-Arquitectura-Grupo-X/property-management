package com.rentstate.property_management.domain.dto.request;


import com.rentstate.property_management.domain.model.valueObjects.Category;
import lombok.Data;

@Data
public class PropertyRequest {


    private String name;
    private String description;
    private String characteristics;
    private String location;
    private Category category;
    private String urlImg;
    private Long ownerId;

}
