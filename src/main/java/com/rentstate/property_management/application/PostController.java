package com.rentstate.property_management.application;

import com.rentstate.property_management.domain.dto.request.PostRequest;
import com.rentstate.property_management.domain.dto.response.PostResponse;
import com.rentstate.property_management.domain.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v17/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> post(@RequestBody PostRequest postRequest) {

        PostResponse postResponse = postService.addPost(postRequest);
        if (postResponse == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
    }
    @GetMapping({"/{postId}"})
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long postId) {
        PostResponse postResponse = postService.getPostById(postId);
        if(postResponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<PostResponse>> getPostByAuthorId(@PathVariable Long authorId) {
        List<PostResponse> postResponses = postService.getPostsByAuthorId(authorId);
        if(postResponses == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postResponses, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        List<PostResponse> postResponses = postService.getAllPosts();
        if(postResponses == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postResponses, HttpStatus.OK);
    }
    @PutMapping("/update/{postId}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long postId, @RequestBody PostRequest postRequest) {
        PostResponse postResponse = postService.updatePost(postRequest,postId);
        if(postResponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<Boolean> deletePost(@PathVariable Long postId) {
        return new ResponseEntity<>(postService.deletePost(postId),HttpStatus.OK);
    }

}
