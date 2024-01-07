package com.example.musicCollection.repo;

import com.example.musicCollection.models.Album;
import com.example.musicCollection.models.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SingerRepository extends JpaRepository<Singer, Long> {
    @Query(value = "SELECT a FROM Album a " +
            "JOIN a.singers s " +
            "WHERE s.id = :singerId")
    Set<Album> findAlbumBySingerId(@Param("singerId") Long singerId);


    Singer findSingerByName(String name);
}
