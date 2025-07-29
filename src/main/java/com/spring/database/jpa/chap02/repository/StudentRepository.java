package com.spring.database.jpa.chap02.repository;

import com.spring.database.jpa.chap02.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// JpaRepository의 제네릭에는 첫번째 엔터티, 두번째 ID 타입
public interface StudentRepository extends JpaRepository<Student,String>{

    // Query Method : 메서드에 특별한 이름규칙을 사용해서 SQL을 생성
    List<Student> findByName(String name);

    // WHERE city = ? AND major = ?
    List<Student> findByCityAndMajor(String city, String major);

    // WHERE major LIKE '%?%'
    List<Student> findByMajorContaining(String major);

    // WHERE major LIKE '?%'
    List<Student> findByMajorStartingWith(String major);

    // WHERE major LIKE '%?'
    List<Student> findByMajorEndingWith(String major);

    // WHERE age <= ?
    // List<Student> findByAgeLessThanEqual(int age);

    // JPQL 사용하기
    // 도시 이름으로 학생 조회하기   테이블 명이 아닌 자바 클래스 이름 기입해야함
    @Query (value = "SELECT s FROM Student s WHERE s.city = ?1")
    List<Student> getStudentByCity(String city);

    // 순수 SQL 사용하기
    // 도시 + 이름으로 학생 조회하기
    @Query (value = """
                    SELECT *
                    FROM tbl_student
                    WHERE city = ?1
                    AND stu_name = ?2
                    """, nativeQuery = true)
    List<Student> getStudents(String city, String name);

}
