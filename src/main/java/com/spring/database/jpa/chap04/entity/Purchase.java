package com.spring.database.jpa.chap04.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@ToString(exclude = {"user","goods"})
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "tbl_mtm_purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private Long id;

    // 회원과 상품의 다대다 관계 해소를 위해서
    // 중간 테이블 Purchase는 회원과 1대 N, 상품과 1대 N로 해소

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private Goods goods;
}
