package com.example.musicCollection.controllers;

import com.example.musicCollection.models.Playlist;
import com.example.musicCollection.models.Song;
import com.example.musicCollection.services.PlaylistService;
import com.example.musicCollection.services.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;
    private final SongService songService;

    @GetMapping("/playlist")
    public String showPlaylist(Model model){
        model.addAttribute("playlists", playlistService.getAllPlaylist());
        model.addAttribute("songs", songService.getAllSongs());
        return "playlist";
    }

    @GetMapping("/playlist/{playlistId}")
    public String showPlaylistSeeMore(@PathVariable Long playlistId, Model model){
        Playlist playlist = playlistService.getPlaylistById(playlistId);
        model.addAttribute("playlist", playlist);
        model.addAttribute("songs", playlist.getSongs());
        return "playlistMore";
    }

    @PostMapping("/addPlaylist")
    public String addPlaylist(@RequestParam("file") MultipartFile file, Playlist playlist) throws IOException {
        playlistService.saveImagePlaylist(playlist, file);
        return "redirect:/playlist";
    }

    @PostMapping("/addSongToPlaylist")
    public String addSongToPlaylist(@RequestParam("selectSong") Long songId, @RequestParam("selectPlaylist") Long playlistId) throws IOException {
        playlistService.saveSongToPlaylist(playlistId, songId);
        return "redirect:/playlist";
    }
}
