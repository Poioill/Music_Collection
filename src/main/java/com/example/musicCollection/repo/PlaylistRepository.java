package com.example.musicCollection.repo;

import com.example.musicCollection.models.Playlist;
import com.example.musicCollection.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByNameContainingIgnoreCase(String name);

    @Query(value = "SELECT sg from Song sg " +
            "JOIN sg.playlists p " +
            "WHERE p.id = :playlistId")
    Set<Song> findSongByPlaylistId(@Param("playlistId") Long playlistId);
}
