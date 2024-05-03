package com.example.musicCollection.services;

import com.example.musicCollection.models.Genre;
import com.example.musicCollection.models.Singer;
import com.example.musicCollection.models.SingerImage;
import com.example.musicCollection.repo.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public List<Genre> listGenre(String name) {
        if (name != null && genreRepository.findByNameContainingIgnoreCase(name).isEmpty()) {
            return genreRepository.findAll();
        } else if (name != null) {
            return genreRepository.findByNameContainingIgnoreCase(name);
        } else return genreRepository.findAll();
    }

    public Set<Singer> findSingerByGenreId(Long id) {
        return genreRepository.findSingerByGenreId(id);
    }

    public Genre getGenreById(Long id) {
        return genreRepository.findById(id).orElseThrow(null);
    }

    public SingerImage toImageEntity(MultipartFile file) throws IOException {
        SingerImage img = new SingerImage();
        img.setName(file.getName());
        img.setOriginalFileName(file.getOriginalFilename());
        img.setContentType(file.getContentType());
        img.setSize(file.getSize());
        img.setBytes(file.getBytes());
        return img;
    }

    public void saveImageGenre(Genre genre,
                          MultipartFile file) throws IOException {
        SingerImage img;
        if (file.getSize() != 0){
            img = toImageEntity(file);
            genre.addImageToGenre(img);
        }
        genre.setName(genre.getName());
        log.info("Saving Genre: {}", genre.getName());
        genreRepository.save(genre);
    }
}
