package com.rentstate.property_management.infrastructure.repository;

import com.rentstate.property_management.domain.model.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByAuthorId(Long authorId);
    Boolean existsByAuthorId(Long authorId);

}
