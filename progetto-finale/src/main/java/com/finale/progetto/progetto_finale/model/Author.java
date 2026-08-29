package com.finale.progetto.progetto_finale.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Author {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotBlank(message = "The author name cannot be empty")
    @Size(min = 1)
    private String name;

    @Column(name = "image_url")
    private String imgUrl;

    @Column(columnDefinition = "TEXT", nullable = false)
    @NotBlank(message = "The author biography cannot be empty and must be at least 20 characters")
    @Size(min = 20)
    private String biography;

    @Column
    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Book> books;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name.length() > 0) {
            this.name = name;
        }
    }

    public String getBiography() {
        return this.biography;
    }

    public void setBiography(String biography) {
        if (biography.length() > 20) {
            this.biography = biography;
        }
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
