package com.spring.database.jpa.practice.entity;


import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode
@Getter
// @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "tbl_artist_list")
public class ArtistList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "artist_name",nullable = false)
    private String name;

    private String Music;

    @Enumerated(EnumType.STRING)
    private country country;

    public enum country{
        KR, USA, UK, OTHERS
    }
}
