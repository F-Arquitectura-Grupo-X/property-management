package com.rentstate.property_management.application;

import com.rentstate.property_management.domain.dto.request.CommentRequest;
import com.rentstate.property_management.domain.dto.response.CommentResponse;
import com.rentstate.property_management.domain.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v17/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> addComment(@RequestBody CommentRequest commentRequest) {
        CommentResponse commentResponse = commentService.addComment(commentRequest);
        if (commentResponse != null) {
            return ResponseEntity.ok(commentResponse);
        }
        return ResponseEntity.badRequest().build();
    }
}
