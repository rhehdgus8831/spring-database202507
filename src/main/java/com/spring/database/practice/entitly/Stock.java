package com.spring.database.practice.entitly;

import lombok.*;

import java.sql.ResultSet;
import java.time.LocalDateTime;

/*
CREATE TABLE stocks (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,           -- 데이터 고유 식별자 (PK)
                        stock_code VARCHAR(20) NOT NULL,                -- 종목 코드 (예: '005930')
                        stock_name VARCHAR(100) NOT NULL,               -- 종목명 (예: '삼성전자')
                        quantity INT NOT NULL,                          -- 보유 수량
                        purchase_price DECIMAL(19, 4) NOT NULL,         -- 매수 단가 (1주당 가격)
                        purchase_date DATE NOT NULL,                    -- 매수일
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- 데이터 생성 시각 (자동 기록)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
*/
@EqualsAndHashCode
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stock {
    private Long id;
    private String stockCode;
    private String stockName;
    private int quantity;
    private double purchasePrice;
    private String purchaseDate;
    private LocalDateTime createdAt;

    public Stock(ResultSet resultSet) throws Exception {

        this.id = resultSet.getLong("id");
        this.stockCode = resultSet.getString("stock_code");
        this.stockName = resultSet.getString("stock_name");
        this.quantity = resultSet.getInt("quantity");
        this.purchasePrice = resultSet.getDouble("purchase_price");
        this.purchaseDate = resultSet.getString("purchase_date");
        this.createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
    }
}