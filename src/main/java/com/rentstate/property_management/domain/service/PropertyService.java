package com.rentstate.property_management.domain.service;

import com.rentstate.property_management.domain.dto.request.PropertyRequest;
import com.rentstate.property_management.domain.dto.response.PropertyResponse;

import java.util.List;

public interface PropertyService {

    PropertyResponse addProperty(PropertyRequest propertyRequest);
    PropertyResponse updateProperty( PropertyRequest propertyRequest,Long propertyId);
    Boolean deleteProperty(Long propertyId);
    PropertyResponse getPropertyById(Long id);
    List<PropertyResponse> getPropertiesByAuthorId(Long ownerId);

}
