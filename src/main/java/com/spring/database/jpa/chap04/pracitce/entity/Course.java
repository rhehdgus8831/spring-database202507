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
@Table(name = "tbl_mtm_course")
public class Course {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id; // 기본키

    @Column(name = "course_title")
    private String title; // 강의명

    @Column(name = "course_instructor")
    private String instructor; // 강사명

    @Column(name = "course_price")
    private Integer price; // 가격

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments = new ArrayList<>();

}
