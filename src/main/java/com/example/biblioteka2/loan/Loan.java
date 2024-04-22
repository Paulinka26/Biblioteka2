package com.example.biblioteka2.loan;

import com.example.biblioteka2.book.Book;
import com.example.biblioteka2.user.User;
import jakarta.persistence.*;
import java.util.Date;

/**
 * Klasa reprezentująca wypożyczenie książki przez użytkownika w systemie bibliotecznym.
 */
@Entity
@Table(name = "loans", schema = "library")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private long loanId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Basic
    @Column(name = "loan_date")
    private Date loanDate;

    @Basic
    @Column(name = "due_date")
    private Date dueDate;

    @Basic
    @Column(name = "return_date")
    private Date returnDate;

    /**
     * Zwraca identyfikator wypożyczenia.
     * @return identyfikator wypożyczenia
     */
    public long getLoanId() {
        return loanId;
    }

    /**
     * Ustawia identyfikator wypożyczenia.
     * @param loanId identyfikator wypożyczenia
     */
    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    /**
     * Zwraca książkę, którą została wypożyczona.
     * @return książka wypożyczona przez użytkownika
     */
    public Book getBook() {
        return book;
    }

    /**
     * Ustawia książkę, którą została wypożyczona.
     * @param book książka wypożyczona przez użytkownika
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Zwraca użytkownika, który wypożyczył książkę.
     * @return użytkownik, który wypożyczył książkę
     */
    public User getUser() {
        return user;
    }

    /**
     * Ustawia użytkownika, który wypożyczył książkę.
     * @param user użytkownik, który wypożyczył książkę
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Zwraca datę wypożyczenia książki.
     * @return data wypożyczenia książki
     */
    public Date getLoanDate() {
        return loanDate;
    }

    /**
     * Ustawia datę wypożyczenia książki.
     * @param loanDate data wypożyczenia książki
     */
    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    /**
     * Zwraca datę spodziewanego zwrotu książki.
     * @return spodziewana data zwrotu książki
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Ustawia datę spodziewanego zwrotu książki.
     * @param dueDate spodziewana data zwrotu książki
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Zwraca datę faktycznego zwrotu książki.
     * @return faktyczna data zwrotu książki
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * Ustawia datę faktycznego zwrotu książki.
     * @param returnDate faktyczna data zwrotu książki
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
