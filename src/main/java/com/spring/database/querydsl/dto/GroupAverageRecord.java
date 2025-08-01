package com.spring.database.querydsl.dto;

import com.querydsl.core.Tuple;
import com.spring.database.querydsl.entity.QIdol;
import lombok.Builder;
import org.springframework.context.annotation.Bean;

@Builder
// 레코드 : 자동 보일러 플레이트 생성 (getter, setter ...)
// 자바 17에서 사용 가능
public record GroupAverageRecord(
        String groupName,
        Double average
) {

    // 정적 팩토리 메서드 패턴 (이름을 마음대로 설정 가능)
    // Tuple을 전달받아서 DTO로 변환하는 메서드
    public static GroupAverageAge from(Tuple tuple) {
        return GroupAverageAge.builder()
                .groupName(tuple.get(QIdol.idol.group.groupName))
                .averageAge(tuple.get(QIdol.idol.age.avg()))
                .build();
    }
}