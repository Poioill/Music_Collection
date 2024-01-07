package com.example.musicCollection.services;

import com.example.musicCollection.models.Genre;
import com.example.musicCollection.models.Singer;
import com.example.musicCollection.repo.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    public Set<Singer> findSingerByGenreId(Long id) {
        return genreRepository.findSingerByGenreId(id);
    }

    public Genre getGenreById(Long id) {
        return genreRepository.findById(id).orElseThrow(null);
    }
}
