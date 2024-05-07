package com.example.musicCollection.controllers;

import com.example.musicCollection.services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class HomeController {
    private final PlaylistService playlistService;

    @GetMapping("/")
    public String getHome(Model model){
        model.addAttribute("playlist", playlistService.getAllPlaylists());
        return "home";
    }
}
