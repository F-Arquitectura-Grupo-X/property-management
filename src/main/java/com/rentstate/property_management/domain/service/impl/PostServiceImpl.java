package com.rentstate.property_management.domain.service.impl;

import com.rentstate.property_management.domain.dto.request.PostRequest;
import com.rentstate.property_management.domain.dto.response.PostResponse;
import com.rentstate.property_management.domain.model.entities.Post;
import com.rentstate.property_management.domain.model.entities.Property;
import com.rentstate.property_management.domain.service.PostService;
import com.rentstate.property_management.infrastructure.repository.PostRepository;
import com.rentstate.property_management.infrastructure.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PropertyRepository propertyRepository;

    @Override
    public PostResponse addPost(PostRequest postRequest) {
        Optional<Property> property = propertyRepository.findById(postRequest.getPropertyId());
        if (property.isEmpty()) return null;

        //VALIDAR QUE EL USUARIO EXISTA

        Property updateProperty = property.get();
        if (updateProperty.getPosted()) return null;

        updateProperty.setPosted(true);

        Post post = new Post(postRequest, updateProperty);
        postRepository.save(post);
        propertyRepository.save(updateProperty);

        return new PostResponse(post);
    }

    @Override
    public PostResponse updatePost(PostRequest postRequest, Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) return null;

        Post updatedPost = post.get();
        updatedPost.setTitle(postRequest.getTitle());
        updatedPost.setPrice(postRequest.getPrice());

        postRepository.save(updatedPost);
        return new PostResponse(updatedPost);
    }

    @Override
    public Boolean deletePost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) return false;

        Property updateProperty = post.get().getProperty();

        postRepository.delete(post.get());
        updateProperty.setPosted(false);
        propertyRepository.save(updateProperty);
        return true;
    }

    @Override
    public PostResponse getPostById(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) return null;

        return new PostResponse(post.get());
    }

    @Override
    public List<PostResponse> getAllPosts() {
        List<Post>posts = postRepository.findAll();
        return posts.stream().map(PostResponse::new).toList();
    }

    @Override
    public List<PostResponse> getPostsByAuthorId(Long authorId) {
        if(postRepository.existsByAuthorId(authorId)){
            List<Post>posts = postRepository.findAllByAuthorId(authorId);
            return posts.stream().map(PostResponse::new).toList();
        }
        return null;
    }

}
