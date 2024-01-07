package com.example.musicCollection.services;

import com.example.musicCollection.models.Album;
import com.example.musicCollection.repo.AlbumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlbumService {
    public final AlbumRepository albumRepository;

    public Album getAlbumById(Long id) {
        return albumRepository.findById(id).orElseThrow(null);
    }
}
