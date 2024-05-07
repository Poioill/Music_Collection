package com.example.musicCollection.controllers;

import com.example.musicCollection.models.Playlist;
import com.example.musicCollection.models.PlaylistImage;
import com.example.musicCollection.models.SingerImage;
import com.example.musicCollection.repo.ImageRepo;
import com.example.musicCollection.repo.PlaylistImageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class PlaylistImageController {
    private final PlaylistImageRepo imageRepo;

    @GetMapping("/playlistImages/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id){
        PlaylistImage img = imageRepo.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", img.getOriginalFileName())
                .contentType(MediaType.valueOf(img.getContentType()))
                .contentLength(img.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(img.getBytes())));
    }
}
