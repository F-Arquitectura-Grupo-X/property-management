package com.rentstate.property_management.domain.service;

import com.rentstate.property_management.domain.dto.request.PostRequest;
import com.rentstate.property_management.domain.dto.response.PostResponse;
import com.rentstate.property_management.infrastructure.repository.PostRepository;

import java.util.List;

public interface PostService {

    PostResponse addPost(PostRequest postRequest);
    PostResponse updatePost(PostRequest postRequest, Long postId);
    Boolean deletePost(Long postId);
    PostResponse getPostById(Long postId);
    List<PostResponse> getAllPosts();
    List<PostResponse> getPostsByAuthorId(Long authorId);


}
