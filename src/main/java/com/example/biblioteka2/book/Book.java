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

    /**
     * Zwraca identyfikator książki.
     * @return identyfikator książki
     */
    public long getBookId() {
        return bookId;
    }

    /**
     * Ustawia identyfikator książki.
     * @param bookId identyfikator książki
     */
    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    /**
     * Zwraca ISBN książki.
     * @return ISBN książki
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Ustawia ISBN książki.
     * @param isbn ISBN książki
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Zwraca tytuł książki.
     * @return tytuł książki
     */
    public String getTitle() {
        return title;
    }

    /**
     * Ustawia tytuł książki.
     * @param title tytuł książki
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Zwraca autora książki.
     * @return autor książki
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Ustawia autora książki.
     * @param author autor książki
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Zwraca wydawcę książki.
     * @return wydawca książki
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Ustawia wydawcę książki.
     * @param publisher wydawca książki
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Zwraca rok wydania książki.
     * @return rok wydania książki
     */
    public int getYearOfPublish() {
        return yearOfPublish;
    }

    /**
     * Ustawia rok wydania książki.
     * @param yearOfPublish rok wydania książki
     */
    public void setYearOfPublish(int yearOfPublish) {
        this.yearOfPublish = yearOfPublish;
    }

    /**
     * Zwraca liczbę dostępnych kopii książki.
     * @return liczba dostępnych kopii książki
     */
    public Integer getAvailableCopies() {
        return availableCopies;
    }

    /**
     * Ustawia liczbę dostępnych kopii książki.
     * @param availableCopies liczba dostępnych kopii książki
     */
    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
