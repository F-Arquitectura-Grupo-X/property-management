package com.rentstate.property_management.domain.service.impl;

import com.rentstate.property_management.domain.dto.request.CommentRequest;
import com.rentstate.property_management.domain.dto.response.CommentResponse;
import com.rentstate.property_management.domain.model.entities.Comment;
import com.rentstate.property_management.domain.model.entities.Post;
import com.rentstate.property_management.domain.service.CommentService;
import com.rentstate.property_management.infrastructure.repository.CommentRepository;
import com.rentstate.property_management.infrastructure.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentsServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public CommentResponse addComment(CommentRequest commentRequest) {
        Optional<Post> post = postRepository.findById(commentRequest.getPostId());
        if (post.isEmpty()) return null;

        //VALIDAR SI EL USUARIO EXISTE

        Post postEntity = post.get();

        Comment comment = new Comment(commentRequest,postEntity);
        commentRepository.save(comment);
        postEntity.getComments().add(comment);
        postRepository.save(postEntity);

        return new CommentResponse(comment);
    }


}
