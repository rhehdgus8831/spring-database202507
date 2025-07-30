package com.spring.database.jpa.chap04.pracitce.repository;

import com.spring.database.jpa.chap04.entity.User;
import com.spring.database.jpa.chap04.pracitce.entity.Course;
import com.spring.database.jpa.chap04.pracitce.entity.Enrollment;
import com.spring.database.jpa.chap04.pracitce.entity.Student;
import com.spring.database.jpa.chap04.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class EnrollmentRepositoryTest {

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EntityManager em;

    private Student s1;
    private Student s2;
    private Student s3;

    private Course c1;
    private Course c2;
    private Course c3;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        s1 = Student.builder().name("김철수").email("kim1234@naver.com").build();
        s2 = Student.builder().name("김영희").email("zerokim123@gmail.com").build();
        s3 = Student.builder().name("곽철용").email("mapobringe@daum.net").build();

        c1 = Course.builder().title("리액트").instructor("마크 저커버그").price(130000).build();
        c2 = Course.builder().title("Oracle SQL").instructor("래리 엘리슨").price(120000).build();
        c3 = Course.builder().title("CUDA FrameWork").instructor("젠슨황").price(150000).build();

        studentRepository.saveAllAndFlush(List.of(s1,s2,s3));
        courseRepository.saveAllAndFlush(List.of(c1,c2,c3));
    }


    @Test
    @DisplayName("학생과 강의를 연결할 수강 신청 정보 테스트")
    void saveTest() {
        //given

        Enrollment enrollment1 = Enrollment.builder().student(s1).course(c1).progressRate(10).completed(false).build();
        Enrollment enrollment2 = Enrollment.builder().student(s1).course(c2).progressRate(20).completed(false).build();
        Enrollment enrollment3 = Enrollment.builder().student(s2).course(c2).progressRate(100).completed(true).build();
        Enrollment enrollment4 = Enrollment.builder().student(s3).course(c3).progressRate(75).completed(false).build();

        //when

        List<Enrollment> saved = enrollmentRepository
                .saveAllAndFlush(List.of(enrollment1, enrollment2, enrollment3, enrollment4));

        em.flush();
        em.clear();

        //then

        // User user = userRepository.findById(saved.get(0).getStudent().getId()).orElseThrow();
        Student student = studentRepository.findById(saved.get(0).getStudent().getId()).orElseThrow();

        System.out.println("================================");
        // System.out.println("user = " + user);
        System.out.println("student = " + student);
        System.out.println("================================");
    }

    @Test
    @DisplayName("김철수의 '리액트' 강의 수강 신청 진도율을 85%로 업데이트해야 한다.")
    void updateTest(){
        //given
        // 1. 김철수 학생과 리액트 강의로 기존 수강신청 데이터를 생성
        Enrollment enrollment = Enrollment.builder()
                .student(s1)
                .course(c1)
                .progressRate(20) // 초기 진도율 20%
                .completed(false)
                .build();
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        // 영속성 컨텍스트 비우기
        em.flush();
        em.clear();

        //when
        // 2. 수정할 수강신청 정보를 조회
        Enrollment foundEnrollment = enrollmentRepository.findById(savedEnrollment.getId()).orElseThrow();

        // 3. 진도율을 85%로 변경
        foundEnrollment.setProgressRate(85);

        // 4. 변경된 엔티티를 저장 (JPA가 변경을 감지하고 UPDATE 실행)
        enrollmentRepository.save(foundEnrollment);

        // 영속성 컨텍스트 비우기
        em.flush();
        em.clear();

        //then
        // 5. 다시 조회하여 진도율이 85%로 변경되었는지 확인
        Enrollment updatedEnrollment = enrollmentRepository.findById(savedEnrollment.getId()).orElseThrow();
        assertEquals(85, updatedEnrollment.getProgressRate());
        System.out.println("Updated Enrollment: " + updatedEnrollment);
    }




}