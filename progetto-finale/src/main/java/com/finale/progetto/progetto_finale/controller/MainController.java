package com.finale.progetto.progetto_finale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/home")
    public String index(Model model) {

        model.addAttribute("page", "home");

        return "home";
    }
}
