package com.example.biblioteka2.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
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
    public Book getOne(long bookId){
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

}
