package com.example.musicCollection.services;

import com.example.musicCollection.models.Album;
import com.example.musicCollection.models.Singer;
import com.example.musicCollection.models.SingerImage;
import com.example.musicCollection.repo.SingerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
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


    //search by singer id
    public Singer getSingerById(Long id) {
        return singerRepository.findById(id).orElseThrow(null);
    }

    // search by singer name
    public Singer findSingerByName(String name) {
        return singerRepository.findSingerByName(name);
    }

    // to get all albums of a certain singer
    public Set<Album> getAllSingerAlbums(Long singerId) {
        return singerRepository.findAlbumBySingerId(singerId);
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
        singer.setId(singer.getId());
        singer.setName(singer.getName());
        log.info("Saving Singer: {}", singer.getName());
        singerRepository.save(singer);
    }

}