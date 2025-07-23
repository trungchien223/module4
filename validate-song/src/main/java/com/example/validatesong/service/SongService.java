package com.example.validatesong.service;

import com.example.validatesong.model.Song;
import com.example.validatesong.repository.ISongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService implements ISongService {
    private final ISongRepository songRepository;
    @Autowired
    public SongService(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }
    @Override
    public void save(Song song) {
        songRepository.save(song);
    }

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        songRepository.deleteById(id);
    }
}
