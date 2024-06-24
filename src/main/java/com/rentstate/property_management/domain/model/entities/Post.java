package com.rentstate.property_management.domain.model.entities;

import com.rentstate.property_management.domain.dto.request.PostRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private Double price;

    @NotNull
    private Date createdAt;

    @NotNull
    private Long authorId;

    @OneToOne
    @JoinColumn(name="property_id")
    @NotNull
    private Property property;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public Post(PostRequest postRequest, Property property) {
        this.title = postRequest.getTitle();
        this.price = postRequest.getPrice();
        this.authorId = property.getOwnerId();
        this.createdAt = new Date();
        this.property = property;
        this.comments = new ArrayList<>();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setPost(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setPost(null);
    }
}
