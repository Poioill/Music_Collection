package com.example.musicCollection.repo;

import com.example.musicCollection.models.PlaylistImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistImageRepo extends JpaRepository<PlaylistImage, Long> {
}
