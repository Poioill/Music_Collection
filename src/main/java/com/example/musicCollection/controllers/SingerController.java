package com.example.musicCollection.controllers;

import com.example.musicCollection.models.Album;
import com.example.musicCollection.models.Singer;
import com.example.musicCollection.models.Song;
import com.example.musicCollection.services.SingerService;
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
public class SingerController {
    public final SingerService singerService;

    @GetMapping("/{singerName}")
    public String singer(@PathVariable String singerName, Model model) {
        Singer singer = singerService.findSingerByName(singerName);
        Set<Album> albums = singerService.getAllSingerAlbums(singer.getId());
        Set<Song> songs = singerService.getAllSingerSongs(singer.getId());
        model.addAttribute("singer", singer);
        model.addAttribute("images", singer.getImages());
        model.addAttribute("albums", albums);
        model.addAttribute("songs", songs);
        return "singer";
    }

    @GetMapping("/addSinger")
    public String addSinger(Model model) {
        return "add/addSinger";
    }

    @PostMapping("/addSinger")
    public String addSinger(@RequestParam("file") MultipartFile file, Singer singer) throws IOException {
        singerService.saveImage(singer, file);
        return "redirect:/";
    }
}
