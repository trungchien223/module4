package com.example.validatesong.controller;

import com.example.validatesong.dto.SongDto;
import com.example.validatesong.model.Song;
import com.example.validatesong.service.ISongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/songs")
public class SongController {
    @Autowired
    private ISongService songService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("songs", songService.findAll());
        return "list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("songDto", new SongDto());
        return "create";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("songDto") SongDto songDto,
                       BindingResult result,
                       RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "create";
        }
        Song song = new Song();
        song.setTitle(songDto.getTitle());
        song.setArtist(songDto.getArtist());
        song.setGenre(songDto.getGenre());
        songService.save(song);
        redirectAttributes.addFlashAttribute("success", "Bài hát đã được thêm thành công!");
        return "redirect:/songs";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Song> optionalSong = songService.findById(id);
        if (optionalSong.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy bài hát");
            return "redirect:/songs";
        }
        Song song = optionalSong.get();
        SongDto songDto = new SongDto();
        songDto.setTitle(song.getTitle());
        songDto.setArtist(song.getArtist());
        songDto.setGenre(song.getGenre());

        model.addAttribute("songDto", songDto);
        model.addAttribute("id", song.getId());
        return "edit";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("songDto") SongDto songDto,
                         BindingResult result,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "edit";
        }
        Optional<Song> optionalSong = songService.findById(id);
        if (optionalSong.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy bài hát");
            return "redirect:/songs";
        }
        Song song = optionalSong.get();
        song.setTitle(songDto.getTitle());
        song.setArtist(songDto.getArtist());
        song.setGenre(songDto.getGenre());

        songService.save(song);
        redirectAttributes.addFlashAttribute("success", "Cập nhật bài hát thành công!");
        return "redirect:/songs";
    }
}
