package com.example.musicCollection.services;

import com.example.musicCollection.models.Album;
import com.example.musicCollection.models.Genre;
import com.example.musicCollection.models.Singer;
import com.example.musicCollection.models.SingerImage;
import com.example.musicCollection.repo.AlbumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlbumService {
    public final AlbumRepository albumRepository;

    public Album getAlbumById(Long id) {
        return albumRepository.findById(id).orElseThrow(null);
    }

    public Set<Singer> findSingerByAlbumId(Long id) {
        return albumRepository.findSingerByAlbumId(id);
    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
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

    public void saveImageAlbum(Album album,
                               MultipartFile file) throws IOException {
        SingerImage img;
        if (file.getSize() != 0){
            img = toImageEntity(file);
            album.addImageToAlbum(img);
        }
        album.setName(album.getName());
        album.setReleaseDate(album.getReleaseDate());
        log.info("Saving Album: {}", album.getName());
        albumRepository.save(album);
    }

    public List<Album> listAlbum(String name) {
        if (name != null && albumRepository.findByNameContainingIgnoreCase(name).isEmpty()) {
            return albumRepository.findAll();
        } else if (name != null) {
            return albumRepository.findByNameContainingIgnoreCase(name);
        } else return albumRepository.findAll();
    }
}
