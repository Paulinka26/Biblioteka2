package com.example.biblioteka2.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private LoanService loanService;

    public class LoanNotFoundException extends RuntimeException {
        public LoanNotFoundException(String message) {
            super(message);
        }
    }

    public class LoanAlreadyExistsException extends RuntimeException {
        public LoanAlreadyExistsException(String message) {
            super(message);
        }
    }

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
        } catch (LoanNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loan not found.");
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Loan loan) {
        try {
            Loan newLoan = loanService.create(loan);
            return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
        } catch (LoanAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Loan already exists.");
        }
    }

    @DeleteMapping("/{loanId}")
    public ResponseEntity<Void> delete(@PathVariable long loanId) {
        try {
            loanService.delete(loanId);
            return ResponseEntity.noContent().build();
        } catch (LoanNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(LoanAlreadyExistsException.class)
    public ResponseEntity<String> handleLoanAlreadyExistsException(LoanAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<String> handleLoanNotFoundException(LoanNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
