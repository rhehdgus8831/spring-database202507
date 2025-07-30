package com.spring.database.jpa.chap04.pracitce.repository;


import com.spring.database.jpa.chap04.pracitce.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
