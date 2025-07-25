package com.spring.database.chap03.practice;

import com.spring.database.chap03.practice.entitly.StockV2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class stockMapperTest {
    
    @Autowired
    stockMapper stockMapper;
    
    @Test
    @DisplayName("save test")
    void saveTest () {
        //given
        StockV2 givenStockV2 = StockV2.builder()
                .stockCode("TLN")
                .stockName("탈런 에너지")
                .quantity(20)
                .purchasePrice(314.00)
                .purchaseDate("2025-07-25")
                .build();
        //when
        boolean save = stockMapper.save(givenStockV2);
        //then
        assertTrue(save);
    }
    
    

}