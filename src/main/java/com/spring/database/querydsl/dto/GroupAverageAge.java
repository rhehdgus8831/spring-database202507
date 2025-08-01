package com.spring.database.querydsl.dto;

import com.querydsl.core.Tuple;
import com.spring.database.querydsl.entity.QGroup;
import com.spring.database.querydsl.entity.QIdol;
import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

// 그룹명과 평균나이를 매핑할 클래스
public class GroupAverageAge {

    private String groupName;
    private Double averageAge;

    // Tuple을 전달받아서 DTO로 변환하는 생성자

    public GroupAverageAge(Tuple tuple) {
        this.groupName = tuple.get(QIdol.idol.group.groupName);
        Double averageAge = tuple.get(QIdol.idol.age.avg());

    }

    // 정적 팩토리 메서드 패턴 (이름을 마음대로 설정 가능)
    // Tuple을 전달받아서 DTO로 변환하는 메서드
    public static GroupAverageAge from(Tuple tuple) {
        return GroupAverageAge.builder()
                .groupName(tuple.get(QIdol.idol.group.groupName))
                .averageAge(tuple.get(QIdol.idol.age.avg()))
                .build();
    }

}
