package com.spring.database.jpa.chap03.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString(exclude = {"department"})
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

// 사원 N
@Entity
@Table(name = "tbl_emp")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id; // 사원번호

    @Column(name = "emp_name", nullable = false)
    private String name; // 사원명

    // DBMS처럼 한쪽(N쪽0에 상대의 데이터를 포함시키는 전략
    // -> 단반향 매핑
    // @ManyToOne 무조건 LAZY를 걸어라
    @ManyToOne(fetch = FetchType.LAZY) // 필요없을 때는 조인을 하지 않는 전략
    @JoinColumn(name = "dept_id") // FK를 포함시키는건 DB 패러다임에 맞춰야함
    private Department department; // 부서정보 통째로 포함 (연관 관계 설정)

    // 부서 변경 편의 메서드
    public void changeDepartment(Department department) {
        // ManyToOne 필드가 변경이 일어나면 반대편쪽이 OneToMany도 같이 갱신
        this.department = department;
        if (department != null) {
            department.getEmployees().add(this);
        }
    }
}