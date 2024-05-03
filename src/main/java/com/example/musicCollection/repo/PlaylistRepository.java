package com.example.musicCollection.repo;

import com.example.musicCollection.models.Genre;
import com.example.musicCollection.models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByNameContainingIgnoreCase(String name);
}
