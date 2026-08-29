package com.finale.progetto.progetto_finale.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.finale.progetto.progetto_finale.model.Author;
import com.finale.progetto.progetto_finale.model.Book;
import com.finale.progetto.progetto_finale.repository.AuthorRepository;
import com.finale.progetto.progetto_finale.repository.BookRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    // Method to get all available books

    @GetMapping
    public String index(Model model) {

        List<Book> allBooks = bookRepository.findAll();

        Map<Integer, Book> oneBookPerAuthor = new LinkedHashMap<>();

        for (Book book : allBooks) {
            if (book.getAuthor() != null) {
                oneBookPerAuthor.putIfAbsent(book.getAuthor().getId(), book);
            }
        }

        model.addAttribute("books", oneBookPerAuthor.values());
        model.addAttribute("page", "books");

        return "/book/index";

    }

    // Method to get the details of the selected book

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        Book book = bookRepository.findById(id).orElse(null);

        if (book == null) {
            return "/book/not-found";
        }

        model.addAttribute("book", book);
        model.addAttribute("page", "books");

        return "/book/show";
    }

    // Method to create a new book

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorRepository.findAll());

        return "/book/create";
    }

    @PostMapping("/create")
    public String store(
            @Valid @ModelAttribute Book book,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("book", book);
            return "/book/create";
        }

        Author author = authorRepository
            .findById(book.getAuthor().getId())
            .orElseThrow();

        book.setAuthor(author);

        System.out.println(book);

        bookRepository.save(book);

        System.out.println("Libro Salvato");

        return "redirect:/books";
    }

    // Method to edit an existing book

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("book", bookRepository.findById(id).get());

        return "/book/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id,
            @Valid @ModelAttribute Book book,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "/book/edit";
        }

        Author author = authorRepository.findById(book.getAuthor().getId())
                .orElseThrow();

        book.setId(id);
        book.setAuthor(author);

        bookRepository.save(book);

        return "redirect:/books";
    }

    // Method to delete an existring book

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        bookRepository.deleteById(id);

        return "redirect:/books";
    }

}
