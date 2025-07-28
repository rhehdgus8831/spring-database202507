package com.spring.database.jpa.practice;

import com.spring.database.jpa.practice.entity.ArtistList;
import org.springframework.data.repository.Repository;

interface ArtistListRepository extends Repository<ArtistList, String> {
}
