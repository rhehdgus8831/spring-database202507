package com.spring.database.jpa.chap04.pracitce.repository;

import com.spring.database.jpa.chap04.pracitce.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
