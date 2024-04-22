package com.example.biblioteka2.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfejs repozytorium służący do operacji na obiektach typu Book w bazie danych.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
