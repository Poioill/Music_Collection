package com.example.musicCollection.repo;

import com.example.musicCollection.models.Album;
import com.example.musicCollection.models.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    @Query(value = "SELECT s FROM Singer s " +
            "JOIN s.albums g " +
            "WHERE g.id = :albumId")
    Set<Singer> findSingerByAlbumId(@Param("albumId") Long albumId);

    List<Album> findByNameContainingIgnoreCase(String name);
}
