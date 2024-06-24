package com.rentstate.property_management.domain.service;

import com.rentstate.property_management.domain.dto.request.CommentRequest;
import com.rentstate.property_management.domain.dto.response.CommentResponse;
import com.rentstate.property_management.infrastructure.repository.CommentRepository;

public interface CommentService {

    CommentResponse addComment(CommentRequest commentRequest);

}
