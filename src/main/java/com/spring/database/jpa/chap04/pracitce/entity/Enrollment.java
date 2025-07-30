package com.spring.database.jpa.chap04.pracitce.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode
@Getter
@Setter
@ToString(exclude = {"student","course"})
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "tbl_mtm_Enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Enrollment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "enroll_date")
    LocalDateTime enrollDate; // 수강 신청일

    @Column(name = "progress_rate")
    private Integer progressRate; // 진도율 0~100

    @Column(name = "is_completed")
    private Boolean completed; // 완료여부



}
