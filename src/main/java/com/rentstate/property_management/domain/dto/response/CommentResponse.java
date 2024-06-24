package com.rentstate.property_management.domain.dto.response;

import com.rentstate.property_management.domain.model.entities.Comment;
import lombok.Data;

import java.util.Date;

@Data
public class CommentResponse {

    private Long id;
    private String commentText;
    private Date createdAt;
    private Long authorId;
    private String authorName;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.commentText = comment.getCommentText();
        this.createdAt = comment.getCreatedAt();
        this.authorId = comment.getAuthorId();
        this.authorName = comment.getAuthorName();
    }
}
