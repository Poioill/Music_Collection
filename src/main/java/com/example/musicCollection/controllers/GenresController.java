package com.example.musicCollection.controllers;

import com.example.musicCollection.models.Genre;
import com.example.musicCollection.models.Singer;
import com.example.musicCollection.services.GenreService;
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
public class GenresController {
    private final GenreService genreService;

    @GetMapping("/genres")
    public String genres(@RequestParam(name = "name", required = false) String name, Model model){
        model.addAttribute("genres", genreService.listGenre(name));
        return "genres";
    }

    @GetMapping("/genres/{genreId}")
    public String genreSingers(@PathVariable Long genreId, Model model) {
        Genre genre = genreService.getGenreById(genreId);
        Set<Singer> singers = genreService.findSingerByGenreId(genreId);
        model.addAttribute("genre", genre);
        model.addAttribute("images", genre.getImages());
        model.addAttribute("songs", genre.getSongs());
        model.addAttribute("singers", singers);
        return "genreSingers";
    }

    @GetMapping("/addGenre")
    public String addGenre() {
        return "add/addGenre";
    }

    @PostMapping("/addGenre")
    public String addGenre(@RequestParam("file") MultipartFile file, Genre genre) throws IOException {
        genreService.saveImageGenre(genre, file);
        return "redirect:/";
    }
}