package com.spring.database.jpa.chap02.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode
@Getter
// @Setter 객체의 불변성으로 인해 X
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "tbl_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; // PK를 String으로

    @Column(name = "stu_name",nullable = false)
    private String name; // 학생이름

    private String city;

    private String major;
}
