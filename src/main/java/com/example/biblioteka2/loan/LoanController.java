package com.example.biblioteka2.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kontroler obsługujący operacje na wypożyczeniach książek w systemie.
 */
@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private LoanService loanService;

    /**
     * Wyjątek sygnalizujący brak znalezienia wypożyczenia.
     */
    public class LoanNotFoundException extends RuntimeException {
        public LoanNotFoundException(String message) {
            super(message);
        }
    }

    /**
     * Wyjątek sygnalizujący próbę utworzenia już istniejącego wypożyczenia.
     */
    public class LoanAlreadyExistsException extends RuntimeException {
        public LoanAlreadyExistsException(String message) {
            super(message);
        }
    }

    /**
     * Konstruktor kontrolera wypożyczeń książek.
     * @param loanService serwis wypożyczeń
     */
    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    /**
     * Pobiera wszystkie wypożyczenia książek z systemu.
     * @return lista wszystkich wypożyczeń książek
     */
    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAll();
    }

    /**
     * Pobiera pojedyncze wypożyczenie na podstawie jego identyfikatora.
     * @param loanId identyfikator wypożyczenia
     * @return odpowiedź HTTP z wypożyczeniem lub informacją o błędzie
     */
    @GetMapping("/{loanId}")
    public ResponseEntity<?> getOne(@PathVariable long loanId) {
        try {
            Loan loan = loanService.getOne(loanId);
            return ResponseEntity.ok(loan);
        } catch (LoanNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loan not found.");
        }
    }

    /**
     * Tworzy nowe wypożyczenie książki w systemie.
     * @param loan dane nowego wypożyczenia
     * @return odpowiedź HTTP z nowo utworzonym wypożyczeniem lub informacją o błędzie
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Loan loan) {
        try {
            Loan newLoan = loanService.create(loan);
            return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
        } catch (LoanAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Loan already exists.");
        }
    }

    /**
     * Usuwa wypożyczenie książki z systemu na podstawie jego identyfikatora.
     * @param loanId identyfikator wypożyczenia do usunięcia
     * @return odpowiedź HTTP informująca o sukcesie lub niepowodzeniu operacji
     */
    @DeleteMapping("/{loanId}")
    public ResponseEntity<Void> delete(@PathVariable long loanId) {
        try {
            loanService.delete(loanId);
            return ResponseEntity.noContent().build();
        } catch (LoanNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obsługuje wyjątek związany z próbą utworzenia już istniejącego wypożyczenia.
     * @param e wyjątek LoanAlreadyExistsException
     * @return odpowiedź HTTP z informacją o konflikcie
     */
    @ExceptionHandler(LoanAlreadyExistsException.class)
    public ResponseEntity<String> handleLoanAlreadyExistsException(LoanAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    /**
     * Obsługuje wyjątek związany z brakiem znalezienia wypożyczenia.
     * @param e wyjątek LoanNotFoundException
     * @return odpowiedź HTTP z informacją o nieznalezieniu wypożyczenia
     */
    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<String> handleLoanNotFoundException(LoanNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
