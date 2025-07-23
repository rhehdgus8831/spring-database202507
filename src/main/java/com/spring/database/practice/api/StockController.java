package com.spring.database.practice.controller; // 패키지 경로는 맞게 수정해주세요.

import com.spring.database.practice.entitly.Stock; // Stock 엔티티 경로
import com.spring.database.practice.repository.StockRepository; // StockRepository 경로
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks") // JS 파일의 const URL = '/api/v1/stocks' 와 일치
@RequiredArgsConstructor
public class StockController {

    private final StockRepository stockRepository;

    // 전체 주식 목록 조회 (GET /api/v1/stocks)
    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stockList = stockRepository.findAll();
        return ResponseEntity.ok(stockList);
    }

    // 단일 주식 정보 조회 (GET /api/v1/stocks/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long id) {
        Stock stock = stockRepository.findById(id);
        return ResponseEntity.ok(stock);
    }

    // 새 주식 등록 (POST /api/v1/stocks)
    @PostMapping
    public ResponseEntity<?> createStock(@RequestBody Stock stock) {
        stockRepository.save(stock);
        return ResponseEntity.ok().body("등록 성공");
    }

    // 주식 정보 수정 (PUT /api/v1/stocks/{id})
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStock(@PathVariable Long id, @RequestBody Stock stockDetails) {
        // 수정 로직을 위해 기존 데이터를 조회
        Stock stock = stockRepository.findById(id);

        // JS에서 보낸 수정 데이터로 덮어쓰기
        stock.setQuantity(stockDetails.getQuantity());
        stock.setPurchasePrice(stockDetails.getPurchasePrice());
        stock.setPurchaseDate(stockDetails.getPurchaseDate());

        // update 메서드 실행
        stockRepository.updateStock(stock);
        return ResponseEntity.ok().body("수정 성공");
    }

    // 주식 정보 삭제 (DELETE /api/v1/stocks/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStock(@PathVariable Long id) {
        stockRepository.deleteById(id);
        return ResponseEntity.ok().body("삭제 성공");
    }
}