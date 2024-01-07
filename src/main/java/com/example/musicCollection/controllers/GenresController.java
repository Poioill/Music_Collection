package com.example.musicCollection.controllers;

import com.example.musicCollection.models.Genre;
import com.example.musicCollection.models.Singer;
import com.example.musicCollection.services.GenreService;
import com.example.musicCollection.services.SingerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@Controller
@RequiredArgsConstructor
public class GenresController {
    private final GenreService genreService;
    private final SingerService singerService;

    @GetMapping("/genres")
    public String genres(Model model){
        model.addAttribute("genres", genreService.findAllGenres());
        return "genres";
    }

    @GetMapping("/genres/{genreId}")
    public String genreSingers(@PathVariable Long genreId, Model model) {
        Genre genre = genreService.getGenreById(genreId);
        Set<Singer> singers = genreService.findSingerByGenreId(genreId);
        model.addAttribute("genre", genre);
        model.addAttribute("singers", singers);
        return "genreSingers";
    }
}