package com.example.biblioteka2.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @ResponseStatus(HttpStatus.CONFLICT)
    public class BookAlreadyExistsException extends RuntimeException {
        public BookAlreadyExistsException(String message) {
            super(message);
        }
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class BookNotFoundException extends RuntimeException {
        public BookNotFoundException(String message) {
            super(message);
        }
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getOne(@PathVariable long bookId) {
        try {
            Book book = bookService.getOne(bookId);
            return ResponseEntity.ok(book);
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found with id: " + bookId);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Book book) {
        try {
            Book newBook = bookService.create(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
        } catch (BookAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Book already exists");
        }
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> delete(@PathVariable long bookId) {
        try {
            bookService.delete(bookId);
            return ResponseEntity.noContent().build();
        } catch (BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<String> handleBookAlreadyExistsException(BookAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
