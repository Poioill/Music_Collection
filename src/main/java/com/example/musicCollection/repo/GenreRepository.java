package com.example.musicCollection.repo;

import com.example.musicCollection.models.Genre;
import com.example.musicCollection.models.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    @Query(value = "SELECT s FROM Singer s " +
            "JOIN s.genres g " +
            "WHERE g.id = :genreId")
    Set<Singer> findSingerByGenreId(@Param("genreId") Long genreId);

    List<Genre> findByNameContainingIgnoreCase(String name);
}
