package com.rentstate.property_management.domain.dto.response;


import com.rentstate.property_management.domain.model.entities.Post;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostResponse {

    private Long id;
    private String title;
    private Double price;
    private String authorName;
    private PropertyResponse property;
    private List<CommentResponse> comments;

    public PostResponse(Post post ) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.price = post.getPrice();
        this.property = new PropertyResponse(post.getProperty());
        this.comments = post.getComments().stream().map(CommentResponse::new).toList();
        this.authorName = "";
    }
}
