package com.finale.progetto.progetto_finale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finale.progetto.progetto_finale.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAuthor_Id(Integer id);
}
