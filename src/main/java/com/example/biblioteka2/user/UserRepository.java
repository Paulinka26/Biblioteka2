package com.example.biblioteka2.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfejs repozytorium użytkowników, zapewniający dostęp do operacji CRUD na encjach użytkowników w bazie danych.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Metoda do pobierania użytkownika na podstawie jego nazwy użytkownika.
     * @param username nazwa użytkownika
     * @return użytkownik znaleziony w bazie danych na podstawie nazwy użytkownika
     */
    User findByUsername(String username);

    /**
     * Metoda sprawdzająca, czy istnieje użytkownik o podanej nazwie użytkownika.
     * @param username nazwa użytkownika
     * @return true, jeśli użytkownik o podanej nazwie istnieje; w przeciwnym razie false
     */
    boolean existsByUsername(String username);
}
