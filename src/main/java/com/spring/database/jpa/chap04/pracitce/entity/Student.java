package com.spring.database.jpa.chap04.pracitce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@ToString(exclude = {"enrollments"})
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "tbl_mtm_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id; // 기본키

    @Column(name = "student_name", nullable = false)
    private String name; // 학생명

    @Column(name = "student_email", nullable = false)
    private String email; // 이메일

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments = new ArrayList<>();
}
