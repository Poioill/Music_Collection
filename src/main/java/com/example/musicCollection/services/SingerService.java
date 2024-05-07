package com.example.musicCollection.services;

import com.example.musicCollection.models.Album;
import com.example.musicCollection.models.Singer;
import com.example.musicCollection.models.SingerImage;
import com.example.musicCollection.models.Song;
import com.example.musicCollection.repo.SingerRepository;
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
public class SingerService {
    private final SingerRepository singerRepository;

    public List<Singer> findAllSingers() {
        return singerRepository.findAll();
    }

    public Singer getSingerById(Long id) {
        return singerRepository.findById(id).orElseThrow(null);
    }

    public Singer findSingerByName(String name) {
        return singerRepository.findSingerByName(name);
    }

    public Set<Album> getAllSingerAlbums(Long singerId) {
        return singerRepository.findAlbumBySingerId(singerId);
    }

    public Set<Song> getAllSingerSongs(Long singerId) {
        return singerRepository.findSongBySingerId(singerId);
    }

    private SingerImage toImageEntity(MultipartFile file) throws IOException {
        SingerImage img = new SingerImage();
        img.setName(file.getName());
        img.setOriginalFileName(file.getOriginalFilename());
        img.setContentType(file.getContentType());
        img.setSize(file.getSize());
        img.setBytes(file.getBytes());
        return img;
    }

    public void saveImage(Singer singer,
                         MultipartFile file) throws IOException {
        SingerImage img;
        if (file.getSize() != 0){
            img = toImageEntity(file);
            singer.addImageToSinger(img);
        }
        singer.setName(singer.getName());
        log.info("Saving Singer: {}", singer.getName());
        singerRepository.save(singer);
    }

}
