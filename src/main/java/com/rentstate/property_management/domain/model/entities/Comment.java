package com.rentstate.property_management.domain.model.entities;

import com.rentstate.property_management.domain.dto.request.CommentRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String commentText;

    @NotNull
    private Date createdAt;

    @NotNull
    private Long authorId;

    private String authorName;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(CommentRequest commentRequest, Post post) {
        this.commentText = commentRequest.getCommentText();
        this.createdAt = new Date();
        this.authorId = commentRequest.getAuthorId();
        this.authorName = commentRequest.getAuthorName();
        this.post = post;
    }
}
