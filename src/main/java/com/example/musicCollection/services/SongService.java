package com.example.musicCollection.services;

import com.example.musicCollection.models.Playlist;
import com.example.musicCollection.models.Song;
import com.example.musicCollection.repo.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;

    public List<Song> listSong(String name) {
        if (name != null && songRepository.findByTrackTitleContainingIgnoreCase(name).isEmpty()) {
            return songRepository.findAll();
        } else if (name != null) {
            return songRepository.findByTrackTitleContainingIgnoreCase(name);
        } else return songRepository.findAll();
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }
}
