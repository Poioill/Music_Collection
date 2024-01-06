package com.example.musicCollection.controllers;

import com.example.musicCollection.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GenresController {
    private final GenreService genreService;

    @GetMapping("/genres")
    public String genres(Model model){
        model.addAttribute("genres", genreService.findAllGenres());
        return "genres";
    }
}
