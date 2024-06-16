package com.example.biblioteka2.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.biblioteka2.book.Book;
import com.example.biblioteka2.book.BookRepository;
import com.example.biblioteka2.user.User;
import com.example.biblioteka2.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<Loan> getAll() {
        return loanRepository.findAll();
    }

    public Loan getOne(long loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
    }

    public Loan create(LoanRequest loanRequest) {
        Book book = bookRepository.findById(loanRequest.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        User user = userRepository.findById(loanRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setLoanDate(loanRequest.getLoanDate());
        loan.setDueDate(loanRequest.getDueDate());
        loan.setReturnDate(loanRequest.getReturnDate());

        return loanRepository.save(loan);
    }

    public void delete(long loanId) {
        if (!loanRepository.existsById(loanId)) {
            throw new RuntimeException("Loan not found");
        }
        loanRepository.deleteById(loanId);
    }
    public Loan update(long loanId, LoanRequest loanRequest) {
        Loan existingLoan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        Book book = bookRepository.findById(loanRequest.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        User user = userRepository.findById(loanRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingLoan.setBook(book);
        existingLoan.setUser(user);
        existingLoan.setLoanDate(loanRequest.getLoanDate());
        existingLoan.setDueDate(loanRequest.getDueDate());
        existingLoan.setReturnDate(loanRequest.getReturnDate());

        return loanRepository.save(existingLoan);
    }


}
