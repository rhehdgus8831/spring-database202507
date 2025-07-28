package com.spring.database.jpa.practice;

import com.spring.database.jpa.practice.entity.ArtistList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<ArtistList, String> {

}
