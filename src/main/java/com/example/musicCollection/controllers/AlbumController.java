package com.example.musicCollection.controllers;

import com.example.musicCollection.models.Album;
import com.example.musicCollection.services.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @GetMapping("/addAlbum")
    public String addAlbum(Model model) {
        return "add/addAlbum";
    }

    @PostMapping("/addAlbum")
    public String addAlbum(@RequestParam("file") MultipartFile file, Album album) throws IOException {
        albumService.saveImageAlbum(album, file);
        return "redirect:/";
    }

    @GetMapping("/albums")
    public String albums(Model model){
        List<Album> albumList = albumService.getAllAlbums();
        model.addAttribute("albums", albumList);
        return "album";
    }
}
