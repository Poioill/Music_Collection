package com.example.musicCollection.controllers;

import com.example.musicCollection.models.Playlist;
import com.example.musicCollection.models.Song;
import com.example.musicCollection.services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;

    @GetMapping("/playlist")
    public String showPlaylist(Model model){
        model.addAttribute("playlists", playlistService.getAllPlaylist());
        return "playlist";
    }

    @PostMapping("/addPlaylist")
    public String addPlaylist(@RequestParam("file") MultipartFile file, Playlist playlist) throws IOException {
        playlistService.saveImagePlaylist(playlist, file);
        return "redirect:/";
    }
}
