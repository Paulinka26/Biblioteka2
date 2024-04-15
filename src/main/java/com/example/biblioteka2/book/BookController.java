package com.example.biblioteka2.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }
    @GetMapping("/{bookId}")
    public Book getOne(@PathVariable long bookId) {
        return bookService.getOne(bookId);
    }
    @PostMapping
    public ResponseEntity<Book> create (@RequestBody Book book){
        var newBook = bookService.create(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> delete(@PathVariable long bookId) {
        bookService.delete(bookId);
        return ResponseEntity.noContent().build();
    }
}
