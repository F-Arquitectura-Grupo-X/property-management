package com.rentstate.property_management.infrastructure.repository;

import com.rentstate.property_management.domain.model.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Boolean existsByPostId(Long postId);
    List<Comment> findByPostId(Long postId);
}
