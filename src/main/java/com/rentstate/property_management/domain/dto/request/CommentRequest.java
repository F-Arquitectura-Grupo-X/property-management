package com.rentstate.property_management.domain.dto.request;

import lombok.Data;

@Data
public class CommentRequest {

    private String commentText;
    private Long authorId;
    private String authorName;
    private Long postId;

}
