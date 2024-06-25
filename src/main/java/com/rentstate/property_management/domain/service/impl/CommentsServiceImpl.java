package com.rentstate.property_management.domain.service.impl;

import com.rentstate.property_management.application.exceptions.NotFoundException;
import com.rentstate.property_management.client.UserClient;
import com.rentstate.property_management.domain.dto.request.CommentRequest;
import com.rentstate.property_management.domain.dto.response.CommentResponse;
import com.rentstate.property_management.domain.dto.response.UserDTO;
import com.rentstate.property_management.domain.model.entities.Comment;
import com.rentstate.property_management.domain.model.entities.Post;
import com.rentstate.property_management.domain.service.CommentService;
import com.rentstate.property_management.infrastructure.repository.CommentRepository;
import com.rentstate.property_management.infrastructure.repository.PostRepository;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentsServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserClient userClient;

    @Override
    public CommentResponse addComment(CommentRequest commentRequest) {
        Optional<Post> post = postRepository.findById(commentRequest.getPostId());
        if (post.isEmpty()) return null;

        try {
            UserDTO user = userClient.getUser(commentRequest.getAuthorId());

            Post postEntity = post.get();

            commentRequest.setAuthorName(user.getName()+" "+user.getLastName());
            Comment comment = new Comment(commentRequest,postEntity);
            commentRepository.save(comment);
            postEntity.getComments().add(comment);
            postRepository.save(postEntity);

            return new CommentResponse(comment);

        }catch (FeignException.NotFound e) {
            throw new NotFoundException("User with id "+commentRequest.getAuthorId()+" not found");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred", e);
        }

    }


}
