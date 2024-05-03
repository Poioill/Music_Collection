package com.example.musicCollection.services;

import com.example.musicCollection.models.Playlist;
import com.example.musicCollection.models.SingerImage;
import com.example.musicCollection.models.Song;
import com.example.musicCollection.repo.PlaylistRepository;
import com.example.musicCollection.repo.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;

    public SingerImage toImageEntity(MultipartFile file) throws IOException {
        SingerImage img = new SingerImage();
        img.setName(file.getName());
        img.setOriginalFileName(file.getOriginalFilename());
        img.setContentType(file.getContentType());
        img.setSize(file.getSize());
        img.setBytes(file.getBytes());
        return img;
    }

    public void saveImagePlaylist(Playlist playlist,
                                  MultipartFile file) throws IOException {
        SingerImage img;
        if (file.getSize() != 0){
            img = toImageEntity(file);
            playlist.addImageToPlaylist(img);
        }
        playlist.setName(playlist.getName());
        log.info("Saving Playlist: {}", playlist.getName());
        playlistRepository.save(playlist);
    }

    public Playlist getPlaylistById(Long id) {
        return playlistRepository.findById(id).orElseThrow(null);
    }

    public List<Playlist> getAllPlaylist(){
        return playlistRepository.findAll();
    }
    public List<Playlist> listPlaylist(String name) {
        if (name != null && playlistRepository.findByNameContainingIgnoreCase(name).isEmpty()) {
            return playlistRepository.findAll();
        } else if (name != null) {
            return playlistRepository.findByNameContainingIgnoreCase(name);
        } else return playlistRepository.findAll();
    }

    public void saveSongToPlaylist(Long playlistId, Long songId) {
        Song song = songRepository.findById(songId).orElse(null);
        Playlist playlist = playlistRepository.findById(playlistId).orElse(null);

        if (song != null && playlist != null) {
            playlist.addSong(song);
            playlistRepository.save(playlist);
        }
    }

}
