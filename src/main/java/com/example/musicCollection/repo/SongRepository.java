package com.example.musicCollection.repo;

import com.example.musicCollection.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByTrackTitleContainingIgnoreCase(String trackTitle);
}
