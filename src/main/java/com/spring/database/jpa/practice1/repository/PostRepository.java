package com.spring.database.jpa.practice1.repository;

import com.spring.database.jpa.practice1.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
