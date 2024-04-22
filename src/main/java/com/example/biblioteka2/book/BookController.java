package com.example.biblioteka2.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kontroler obsługujący operacje na książkach w systemie.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    /**
     * Konstruktor kontrolera książek.
     * @param bookService serwis książek
     */
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Wyjątek sygnalizujący konflikt podczas próby dodania już istniejącej książki.
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    public class BookAlreadyExistsException extends RuntimeException {
        public BookAlreadyExistsException(String message) {
            super(message);
        }
    }

    /**
     * Wyjątek sygnalizujący brak znalezienia książki o podanym identyfikatorze.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class BookNotFoundException extends RuntimeException {
        public BookNotFoundException(String message) {
            super(message);
        }
    }

    /**
     * Pobiera wszystkie książki z systemu.
     * @return lista wszystkich książek
     */
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    /**
     * Pobiera pojedynczą książkę na podstawie jej identyfikatora.
     * @param bookId identyfikator książki
     * @return odpowiedź HTTP z książką lub informacją o błędzie
     */
    @GetMapping("/{bookId}")
    public ResponseEntity<?> getOne(@PathVariable long bookId) {
        try {
            Book book = bookService.getOne(bookId);
            return ResponseEntity.ok(book);
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found with id: " + bookId);
        }
    }

    /**
     * Tworzy nową książkę w systemie.
     * @param book dane nowej książki
     * @return odpowiedź HTTP z nowo utworzoną książką lub informacją o błędzie
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Book book) {
        try {
            Book newBook = bookService.create(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
        } catch (BookAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Book already exists");
        }
    }

    /**
     * Usuwa książkę z systemu na podstawie jej identyfikatora.
     * @param bookId identyfikator książki do usunięcia
     * @return odpowiedź HTTP informująca o sukcesie lub niepowodzeniu operacji
     */
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> delete(@PathVariable long bookId) {
        try {
            bookService.delete(bookId);
            return ResponseEntity.noContent().build();
        } catch (BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obsługuje wyjątek związany z próbą dodania już istniejącej książki.
     * @param e wyjątek BookAlreadyExistsException
     * @return odpowiedź HTTP z informacją o konflikcie
     */
    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<String> handleBookAlreadyExistsException(BookAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    /**
     * Obsługuje wyjątek związany z brakiem znalezienia książki o podanym identyfikatorze.
     * @param e wyjątek BookNotFoundException
     * @return odpowiedź HTTP z informacją o nieznalezieniu książki
     */
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
