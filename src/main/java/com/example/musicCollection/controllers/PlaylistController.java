package com.example.musicCollection.controllers;

import com.example.musicCollection.models.Playlist;
import com.example.musicCollection.services.PlaylistService;
import com.example.musicCollection.services.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;
    private final SongService songService;


    @GetMapping("/playlist")
    public String showPlaylist(@RequestParam(name = "name", required = false) String name, Model model){
        model.addAttribute("playlists", playlistService.listPlaylist(name));
        model.addAttribute("playlist", playlistService.getAllPlaylists());
        model.addAttribute("songs", songService.getAllSongs());
        return "playlist";
    }

    @GetMapping("/playlist/{id}")
    public String showPlaylistSeeMore(@PathVariable Long id, Model model){
        Playlist playlist = playlistService.getPlaylistById(id);
        model.addAttribute("playl", playlist);
        model.addAttribute("playlist", playlistService.getAllPlaylists());
        model.addAttribute("songs", playlistService.getSongByPlaylistId(id));
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
