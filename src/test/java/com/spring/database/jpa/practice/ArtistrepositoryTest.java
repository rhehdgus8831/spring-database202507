package com.spring.database.jpa.practice;

import com.spring.database.jpa.practice.entity.ArtistList;
import com.spring.database.jpa.practice.entity.ArtistList.country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.spring.database.jpa.practice.entity.ArtistList.country.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArtistRepositoryTest {

    @Autowired
    ArtistRepository artistRepository;

    @BeforeEach
    void bulkSave(){
    ArtistList A1 = ArtistList.builder()
            .name("GD")
            .Music("POWER")
            .country(KR)
            .build();

    ArtistList A2 = ArtistList.builder()
            .name("Bruno Mars")
            .Music("That What I Like")
            .country(USA)
            .build();

    ArtistList A3 = ArtistList.builder()
            .name("coldplay")
            .Music("viva la vida")
            .country(UK)
            .build();

    artistRepository.saveAllAndFlush(
            List.of(A1,A2,A3)
    );
    }

@Test
@DisplayName("아티스트를 저장")
void saveTest() {
    //given
    ArtistList artist = ArtistList.builder()
            .name("BLACKPINK")
            .Music("뛰어")
            .country(KR)
            .build();
    //when
    artistRepository.save(artist);
    //then
    assertTrue(true);
}



}