package com.example.biblioteka2.book;

import jakarta.persistence.*;

/**
 * Klasa reprezentująca książkę w systemie bibliotecznym.
 */
@Entity
@Table(name = "books", schema = "library")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long bookId;

    @Basic
    @Column(name = "isbn", unique = true)
    private String isbn;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "author")
    private String author;

    @Basic
    @Column(name = "publisher")
    private String publisher;

    @Basic
    @Column(name = "year_of_publish")
    private int yearOfPublish;

    @Basic
    @Column(name = "available_copies")
    private Integer availableCopies;


    public long getBookId() {
        return bookId;
    }


    public void setBookId(long bookId) {
        this.bookId = bookId;
    }


    public String getIsbn() {
        return isbn;
    }


    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }


    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }


    public int getYearOfPublish() {
        return yearOfPublish;
    }


    public void setYearOfPublish(int yearOfPublish) {
        this.yearOfPublish = yearOfPublish;
    }
    public Integer getAvailableCopies() {
        return availableCopies;
    }


    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
