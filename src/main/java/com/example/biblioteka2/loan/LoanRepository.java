package com.example.biblioteka2.loan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfejs repozytorium służący do operacji na obiektach typu Loan w bazie danych.
 */
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

}
