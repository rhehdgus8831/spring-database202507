package com.spring.database.chap03.practice;

import com.spring.database.chap03.practice.entitly.StockV2;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface stockMapper {

    boolean save(StockV2 stockV2);
    boolean updateStock(StockV2 stockV2);
    boolean deleteById(Long id);
    StockV2 findById(Long id);
    StockV2 findAll();

}
