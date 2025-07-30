package com.spring.database.jpa.chap04.pracitce.repository;



import com.spring.database.jpa.chap04.pracitce.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
