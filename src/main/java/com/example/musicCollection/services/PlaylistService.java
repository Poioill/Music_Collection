package com.example.musicCollection.services;

import com.example.musicCollection.models.Playlist;
import com.example.musicCollection.models.PlaylistImage;
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

    public PlaylistImage toImageEntity(MultipartFile file) throws IOException {
        PlaylistImage img = new PlaylistImage();
        img.setName(file.getName());
        img.setOriginalFileName(file.getOriginalFilename());
        img.setContentType(file.getContentType());
        img.setSize(file.getSize());
        img.setBytes(file.getBytes());
        return img;
    }

    public void saveImagePlaylist(Playlist playlist,
                                  MultipartFile file) throws IOException {
        PlaylistImage img;
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

    public Set<Song> getSongByPlaylistId(Long playlistId){
        return playlistRepository.findSongByPlaylistId(playlistId);
    }

    public List<Playlist> getAllPlaylists() {return playlistRepository.findAll();}

    public List<Playlist> listPlaylist(String name) {
        if (name != null && playlistRepository.findByNameContainingIgnoreCase(name).isEmpty()) {
            return playlistRepository.findAll();
        } else if (name != null) {
            return playlistRepository.findByNameContainingIgnoreCase(name);
        } else return playlistRepository.findAll();
    }

    public void saveSongToPlaylist(Long playlistId, Long songId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow();

        Song song = new Song();
        song.setId(songId);

        playlist.getSongs().add(song);
        song.getPlaylists().add(playlist);

        playlistRepository.save(playlist);
    }

}
