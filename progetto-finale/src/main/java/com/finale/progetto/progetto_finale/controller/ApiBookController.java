package com.finale.progetto.progetto_finale.controller;

import com.finale.progetto.progetto_finale.repository.BookRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finale.progetto.progetto_finale.model.Book;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/library")
@CrossOrigin(origins = "http://localhost:5173")
public class ApiBookController {

    private final BookRepository bookRepository;

    ApiBookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> index() {

        return bookRepository.findAll();

    }

    @GetMapping("/{id}")
    public Book show(@PathVariable Integer id) {

        return bookRepository.findById(id).get();
    }

}
