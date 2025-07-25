package com.spring.database.chap03.practice.api; // 패키지 경로는 맞게 수정해주세요.

import com.spring.database.chap03.practice.entitly.StockV2;
import com.spring.database.chap03.practice.stockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks") // JS 파일의 const URL = '/api/v1/stocks' 와 일치
@RequiredArgsConstructor
public class StockControllerV2 {


    private final stockMapper stockMapper;

    // 전체 주식 목록 조회 (GET /api/v1/stocks)
    @GetMapping
    public ResponseEntity<List<StockV2>> getAllStocks() {
        List<StockV2> stockV2List = Collections.singletonList(stockMapper.findAll());
        return ResponseEntity.ok(stockV2List);
    }

    // 단일 주식 정보 조회 (GET /api/v1/stocks/{id})
    @GetMapping("/{id}")
    public ResponseEntity<StockV2> getStockById(@PathVariable Long id) {
        StockV2 stockV2 = stockMapper.findById(id);
        return ResponseEntity.ok(stockV2);
    }

    // 새 주식 등록 (POST /api/v1/stocks)
    @PostMapping
    public ResponseEntity<?> createStock(@RequestBody StockV2 stockV2) {
        stockMapper.save(stockV2);
        return ResponseEntity.ok().body("등록 성공");
    }

    // 주식 정보 수정 (PUT /api/v1/stocks/{id})
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStock(@PathVariable Long id, @RequestBody StockV2 stockV2Details) {
        // 수정 로직을 위해 기존 데이터를 조회
        StockV2 stockV2 = stockMapper.findById(id);

        // JS에서 보낸 수정 데이터로 덮어쓰기
        stockV2.setQuantity(stockV2Details.getQuantity());
        stockV2.setPurchasePrice(stockV2Details.getPurchasePrice());
        stockV2.setPurchaseDate(stockV2Details.getPurchaseDate());

        // update 메서드 실행
        stockMapper.updateStock(stockV2);
        return ResponseEntity.ok().body("수정 성공");
    }

    // 주식 정보 삭제 (DELETE /api/v1/stocks/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStock(@PathVariable Long id) {
        stockMapper.deleteById(id);
        return ResponseEntity.ok().body("삭제 성공");
    }
}