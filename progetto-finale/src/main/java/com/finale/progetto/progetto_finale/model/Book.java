package com.finale.progetto.progetto_finale.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
public class Book {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotBlank(message = "The field 'name' cannot be empty")
    private String name;

    @Column
    @NotBlank(message = "The ISBN code cannot be empty")
    @Size(min = 13, max = 13, message = "The ISBN must be exactly 13 characters")
    private String ISBN;

    @Column(name = "publishing_house")
    @NotBlank(message = "The publishing house cannot be empty")
    private String publishingHouse;

    @Column
    @Lob
    @Size(min = 1500, max = 2000, message = "The synopsis must be between 1500 and 2000 characters")
    private String synopsis;

    @NotNull(message = "The publication date cannot be empty")
    @PastOrPresent(message = "The publication date cannot be in the future")
    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column
    @NotBlank(message = "The genre field cannot be empty. Please select at least one genre")
    private String genre;

    @Column
    @NotBlank(message = "The language field cannot be empty. Please select at least one language")
    private String language;

    @Column
    @NotNull(message = "The price section cannot be empty. Please insert the price of the book")
    private Float price;

    @NotNull(message = "You must insert at least one author")
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "author_id")
    private Author author;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPublishingHouse() {
        return this.publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getSynopsis() {
        return this.synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public LocalDate getPublicationDate() {
        return this.publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

}