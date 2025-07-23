package com.example.validatesong.service;

import com.example.validatesong.model.Song;
import java.util.List;
import java.util.Optional;

public interface ISongService {
    void save(Song song);

    List<Song> findAll();

    Optional<Song> findById(Long id);

    void deleteById(Long id);
}
