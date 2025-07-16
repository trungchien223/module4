package com.example.emailbox.controller;

import com.example.emailbox.entity.MailConfig;
import com.example.emailbox.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
public class MailConfigController {

    @Autowired
    private IMailService mailService;

    @ModelAttribute("languages")
    public List<String> languages() {
        return Arrays.asList("English", "Vietnamese", "Japanese", "Chinese");
    }

    @ModelAttribute("pageSizes")
    public List<Integer> pageSizes() {
        return Arrays.asList(5, 10, 15, 25, 50, 100);
    }

    @GetMapping({"/mail", "/mail/show"})
    public String showConfig(Model model) {
        model.addAttribute("mailConfig", mailService.getConfig());
        return "show";
    }

    @GetMapping("/mail/edit")
    public String showEditForm(Model model) {
        model.addAttribute("mailConfig", mailService.getConfig());
        return "update";
    }

    @PostMapping("/mail/update")
    public String updateConfig(@ModelAttribute("mailConfig") MailConfig mailConfig,
                               RedirectAttributes redirectAttributes) {
        mailService.updateConfig(mailConfig);
        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật cấu hình thành công!");
        return "redirect:/mail";
    }
}
