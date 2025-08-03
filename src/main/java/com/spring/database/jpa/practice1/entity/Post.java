package com.spring.database.jpa.practice1.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tbl-post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, name = "post-writer")
    private String writer; // 저자

    @Column(length = 100, nullable = false, name = "post-title")
    private String title; // 제목

    @Column(length = 1000, nullable = false, name = "post-content")
    private String content; // 내용


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostTag> postTags = new ArrayList<>();


    /**
     * Post와 Tag 생성 후 PostTag에 연결하는 메서드
     * @param tag
     */
    public void addPostTag(Tag tag) {

        PostTag postTag = PostTag.builder()
                .post(this)
                .tag(tag)
                .build();

        this.postTags.add(postTag);
    }

}
