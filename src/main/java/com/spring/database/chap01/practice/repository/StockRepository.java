package com.spring.database.chap01.practice.repository;

import com.spring.database.chap01.practice.entitly.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository

// DB 접근하여 CRUD 수행
public class StockRepository {

    private final DataSource dataSource;

    // 주식 생성 기능
    public boolean save(Stock stock){


        try(Connection conn = dataSource.getConnection()) {

            String sql = """
                    INSERT INTO stocks
                    (stock_code, stock_name, quantity, purchase_price, purchase_date)
                    VALUES
                    (?, ?, ?, ?, ?)
                    """;
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,stock.getStockCode());
            pstmt.setString(2,stock.getStockName());
            pstmt.setInt(3,stock.getQuantity());
            pstmt.setDouble(4,stock.getPurchasePrice());
            pstmt.setString(5,stock.getPurchaseDate());

           return pstmt.executeUpdate() == 1;


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    // 수량,매수단가,매수일 변경
    public boolean updateStock(Stock stock){
        try(Connection conn = dataSource.getConnection()){

            String sql = """
                    UPDATE stocks
                        SET quantity = ?, purchase_price = ?, purchase_date = ?
                        WHERE id = ?
                    """;

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,stock.getQuantity());
            pstmt.setDouble(2,stock.getPurchasePrice());
            pstmt.setString(3,stock.getPurchaseDate());
            pstmt.setLong(4,stock.getId());

            return pstmt.executeUpdate() == 1;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // 주식 정보 삭제
    public boolean deleteById(Long id){

        try(Connection conn = dataSource.getConnection()){

            String sql = """
                    Delete from stocks
                    WHERE id = ? 
                   
                    
                    """;

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1,id);

            return pstmt.executeUpdate() == 1;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    // 전체 조회
    public List<Stock> findAll(){

        ArrayList<Stock> stockList = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()){

            String sql = """
                    select * from stocks
                    """;

            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()){

                stockList.add(new Stock(resultSet));
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return stockList;
    }

    // ID로 단일 메서드 조회
    public Stock findById(Long id){

        try (Connection conn = dataSource.getConnection()){

            String sql = """
                    select * from stocks
                    where id = ?
                    """;

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1,id);

            ResultSet resultSet = pstmt.executeQuery();

            if(resultSet.next()){
                return new Stock(resultSet);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;

    }




}

