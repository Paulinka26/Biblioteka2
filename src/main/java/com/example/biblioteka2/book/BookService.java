package com.example.biblioteka2.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {

    private BookRepository bookRepository;


    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> getAll(){
        return bookRepository.findAll();
    }


    public Book getBookId(long bookId){
        return bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException("Book not found"));
    }


    public Book create(Book book){
        return bookRepository.save(book);
    }


    public void delete(long bookId){
        if(!bookRepository.existsById(bookId)){
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(bookId);
    }


    public Book update(long bookId, Book newBookData) {
        return bookRepository.findById(bookId)
                .map(book -> {
                    book.setTitle(newBookData.getTitle());
                    book.setAuthor(newBookData.getAuthor());
                    book.setIsbn(newBookData.getIsbn());
                    // Dodaj inne pola do zaktualizowania
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
    }

}
