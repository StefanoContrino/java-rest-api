package com.finale.progetto.progetto_finale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.finale.progetto.progetto_finale.model.Author;
import com.finale.progetto.progetto_finale.repository.AuthorRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // Method to see all authors

    @GetMapping
    public String index(Model model) {

        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("page", "authors");

        return "/author/index";
    }

    // Method to see a specific author

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        Author author = authorRepository.findById(id).orElse(null);

        if (author == null) {
            return "/author/not-found";
        }

        model.addAttribute("author", author);
        model.addAttribute("page", "authors");

        return "/author/show";
    }

    // Method to create a new author

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("author", new Author());

        return "/author/create";
    }

    @PostMapping("/create")
    public String store(
            @Valid @ModelAttribute Author author,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("author", author);
            return "/author/create";
        }

        authorRepository.save(author);

        return "redirect:/authors";
    }

}
