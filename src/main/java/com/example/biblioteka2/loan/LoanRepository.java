package com.example.biblioteka2.loan;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface LoanRepository extends JpaRepository<Loan, Long> {

}
