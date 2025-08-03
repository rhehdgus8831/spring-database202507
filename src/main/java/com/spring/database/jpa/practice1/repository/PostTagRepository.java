package com.spring.database.jpa.practice1.repository;

import com.spring.database.jpa.practice1.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
}
