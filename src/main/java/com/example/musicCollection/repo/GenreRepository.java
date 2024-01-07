package com.example.musicCollection.repo;

import com.example.musicCollection.models.Genre;
import com.example.musicCollection.models.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    @Query(value = "SELECT s FROM Singer s " +
            "JOIN s.genres g " +
            "WHERE g.id = :genreId")
    Set<Singer> findSingerByGenreId(@Param("genreId") Long genreId);
}

//    @Query(value = "SELECT singer.name FROM singer " +
//            "INNER JOIN singer_genre ON singer.id = singer_genre.singer_id " +
//            "INNER JOIN genre ON genre.id = singer_genre.genre_id " +
//            "WHERE genre.id = :genreId;", nativeQuery = true)
//    Set<Singer> findSingerByGenreId(@Param("genreId") Long genreId);
//}
