package com.spring.database.jpa.practice1.repository;

import com.spring.database.jpa.practice1.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
