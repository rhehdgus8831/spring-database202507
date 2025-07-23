package com.spring.database.practice.repository;

import com.spring.database.practice.entitly.Stock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockRepositoryTest {

    @Autowired
    StockRepository stockRepository;


    @Test
    @DisplayName("주식의 이름,티커,수량,매수단가,매수일을 입력해야함")
    void saveTest() {
        //given
        Stock stock = Stock.builder()
                .stockCode("PLTR")
                .stockName("팔란티어")
                .quantity(100)
                .purchasePrice(86.00)
                .purchaseDate("2025-01-01")
                .build();
        //when

        boolean flag = stockRepository.save(stock);

        //then

        assertTrue(flag);
        System.out.println("flag = " + flag);

    }

    @Test
    @DisplayName("수량,매수단가,날짜,ID입력 시 수정")
    void updateStockTest() {
        //given
        Stock givenStock = Stock.builder()
                .id(1L)
                .quantity(50)
                .purchasePrice(65000.00)
                .purchaseDate("2025-03-02")
                .build();
        //when

        boolean flag = stockRepository.updateStock(givenStock);

        //then

        assertTrue(flag);
        System.out.println("flag = " + flag);
    }

    @Test
    @DisplayName("ID를 입력하면 주식 정보가 삭제됨")
    void deleteByIdTest() {
        //given

        Long givenId = 1L;
        //when

        boolean flag = stockRepository.deleteById(givenId);
        //then

        assertTrue(flag);
        System.out.println("flag = " + flag);
    }

    @Test
    @DisplayName("전체 조회를 하면 주식 리스트 반환")
    void findAllTest() {
        //given

        //when

        List<Stock> stockList = stockRepository.findAll();

        //then

        stockList.forEach(System.out::println);
        assertEquals(2,stockList.size());

    }

    @Test
    @DisplayName("ID로 개별 주식 조회")
    void findByIdTest() {
        //given

        Long givenId = 3L;

        //when

        Stock foundStock = stockRepository.findById(givenId);

        //then

        System.out.println("foundStock = " + foundStock);
        assertNotNull(foundStock);


    }




}