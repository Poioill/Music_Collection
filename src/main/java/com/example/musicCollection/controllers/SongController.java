package com.example.musicCollection.controllers;

import com.example.musicCollection.models.Song;
import com.example.musicCollection.services.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class SongController {
    private final SongService songService;

    @PostMapping("/playlist/addSong")
    public String addSongToPlaylist(@ModelAttribute("playlist") Song song,
                                   @RequestParam("select") Long playlistId) {
//        songService.addSong(song, playlistId);
        return "redirect:/";
    }
}
