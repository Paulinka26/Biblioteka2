package com.example.biblioteka2.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin(origins = "http://localhost:3000")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAll();
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<?> getOne(@PathVariable long loanId) {
        try {
            Loan loan = loanService.getOne(loanId);
            return ResponseEntity.ok(loan);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loan not found.");
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody LoanRequest loanRequest) {
        try {
            Loan newLoan = loanService.create(loanRequest);
            return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/{loanId}")
    public ResponseEntity<Void> delete(@PathVariable long loanId) {
        try {
            loanService.delete(loanId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{loanId}")
    public ResponseEntity<?> update(@PathVariable long loanId, @RequestBody LoanRequest loanRequest) {
        try {
            Loan updatedLoan = loanService.update(loanId, loanRequest);
            return ResponseEntity.ok(updatedLoan);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loan not found.");
        }
    }
}
