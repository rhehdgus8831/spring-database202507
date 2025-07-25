package com.spring.database.chap03.entity;

/*
 CREATE TABLE tbl_pet (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         pet_name VARCHAR(50),
                         pet_age INT,
                         injection BOOLEAN
);
*/

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pet {

    private Long id;
    private String petName;
    private int petAge;
    private boolean injection;
}
