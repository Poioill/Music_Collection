package com.example.musicCollection.controllers;

import com.example.musicCollection.models.Album;
import com.example.musicCollection.models.Genre;
import com.example.musicCollection.models.Singer;
import com.example.musicCollection.services.AlbumService;
import com.example.musicCollection.services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;
    private final PlaylistService playlistService;

    @GetMapping("/addAlbum")
    public String addAlbum(Model model) {
        return "add/addAlbum";
    }

    @PostMapping("/addAlbum")
    public String addAlbum(@RequestParam("file") MultipartFile file, Album album) throws IOException {
        albumService.saveImageAlbum(album, file);
        return "redirect:/";
    }

    @GetMapping("/album")
    public String album(@RequestParam(name = "name", required = false) String name, Model model){
        model.addAttribute("albums", albumService.listAlbum(name));
        model.addAttribute("playlist", playlistService.getAllPlaylists());
        return "album";
    }

    @GetMapping("/album/{albumId}")
    public String albumItem(@PathVariable Long albumId, Model model) {
        Album album = albumService.getAlbumById(albumId);
        Set<Singer> singers = albumService.findSingerByAlbumId(albumId);
        model.addAttribute("images", album.getImages());
        model.addAttribute("album", album);
        model.addAttribute("singers", singers);
        model.addAttribute("playlist", playlistService.getAllPlaylists());
        return "/add/albumItem";
    }
}
