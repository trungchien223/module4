package com.example.dictionaryconversion.controller;

import com.example.dictionaryconversion.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("/translate")
    public String showForm() {
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam("word") String word, Model model) {
        String result = dictionaryService.translate(word);
        model.addAttribute("word", word);
        model.addAttribute("result", result);
        if (result == null || result.isEmpty()) {
            model.addAttribute("message", "Không tìm thấy từ '" + word + "' trong từ điển. Vui lòng thử lại!");
        } else {
            model.addAttribute("message", "");
        }
        return "result";
    }
}