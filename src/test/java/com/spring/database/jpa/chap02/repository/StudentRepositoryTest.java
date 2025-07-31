package com.spring.database.jpa.chap02.repository;

import com.spring.database.jpa.chap02.entity.Students;
import com.spring.database.jpa.chap04.pracitce.entity.Student;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
// @Transactional
class StudentRepositoryTest {

    @Autowired
    StudentsRepository studentsRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    void bulkSave(){
        Students s1 = Students.builder()
                .name("쿠로미")
                .city("청양군")
                .major("경제학")
                .build();

        Students s2 = Students.builder()
                .name("춘식이")
                .city("서울시")
                .major("컴퓨터공학")
                .build();

        Students s3 = Students.builder()
                .name("어피치")
                .city("제주도")
                .major("화학공학")
                .build();

        studentsRepository.saveAllAndFlush(
                List.of(s1,s2,s3)
        );
    }

    @Test
    @DisplayName("이름이 춘식이인 학생의 모든 정보를 조회한다.")
    void test1() {
        //given
        String name = "춘식이";
        //when
        List<Students> students = studentsRepository.findByName(name);
        //then
        assertEquals(1,students.size());
        assertEquals("컴퓨터공학",students.get(0).getMajor());

        System.out.println("students = " + students.get(0));
    }

    @Test
    @DisplayName("도시명과 전공명으로 조회")
    void test2() {
        //given
        String city = "제주도";
        String major = "화학공학";
        //when
        // WHERE city = ? AND major = ?
        List<Students> students = studentsRepository.findByCityAndMajor(city, major);
        //then
        students.forEach(System.out::println);
    }

    @Test
    @DisplayName("전공에 공학이 포함된 학생들 조회")
    void test3() {
        //given
        String containingMajor = "공학";
        //when
        List<Students> students = studentsRepository.findByMajorContaining(containingMajor);
        //then
        students.forEach(System.out::println);
    }

    @Test
    @DisplayName("JPQL로 조회해보기")
    void jpqlTest() {
        //given
        String city = "서울시";
        //when
        List<Students> students = studentsRepository.getStudentByCity(city);
        //then
        students.forEach(System.out::println);
    }

    @Test
    @DisplayName("순수 SQL로 조회하기")
    void nativeSqlTest() {
        //given
        String name = "어피치";
        String city = "제주도";
        //when
        List<Students> students = studentsRepository.getStudents(name, city);
        //then
        students.forEach(System.out::println);
    }

    @Test
    @DisplayName("데이터 잘라서 가져오기")
    void pagingTest() {

        for (int i = 0; i < 1000; i++) {
            Students students = Students.builder()
                    .name("가상의 이름" + i)
                    .major("가상의 전공" + i)
                    .city("가상의 도시" + i)
                    .build();
            studentsRepository.save(students);
        }

        String sql = """
                SELECT * FROM tbl_student
                    ORDER BY  stu_name
                LIMIT 5 OFFSET 0
                """;

        List<Students> resultList = (List<Students>) em.createNativeQuery(sql, Students.class)
                .getResultList();

        resultList.forEach(System.out::println);
    }


}