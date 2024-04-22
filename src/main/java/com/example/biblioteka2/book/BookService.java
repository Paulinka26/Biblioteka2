package com.example.biblioteka2.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serwis obsługujący operacje na książkach w systemie.
 */
@Service
public class BookService {

    private BookRepository bookRepository;

    /**
     * Konstruktor serwisu książek.
     * @param bookRepository repozytorium książek
     */
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Pobiera wszystkie książki z systemu.
     * @return lista wszystkich książek
     */
    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    /**
     * Pobiera pojedynczą książkę na podstawie jej identyfikatora.
     * @param bookId identyfikator książki
     * @return książka o podanym identyfikatorze
     * @throws RuntimeException jeśli książka nie zostanie znaleziona
     */
    public Book getOne(long bookId){
        return bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException("Book not found"));
    }

    /**
     * Tworzy nową książkę w systemie.
     * @param book dane nowej książki
     * @return nowo utworzona książka
     */
    public Book create(Book book){
        return bookRepository.save(book);
    }

    /**
     * Usuwa książkę z systemu na podstawie jej identyfikatora.
     * @param bookId identyfikator książki do usunięcia
     * @throws RuntimeException jeśli książka nie zostanie znaleziona
     */
    public void delete(long bookId){
        if(!bookRepository.existsById(bookId)){
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(bookId);
    }

}
