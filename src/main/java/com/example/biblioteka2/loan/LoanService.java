package com.example.biblioteka2.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.util.List;

@Service
public class LoanService {
    private LoanRepository loanRepository;
    @Autowired
    public LoanService(LoanRepository bookRepository) {
        this.loanRepository = bookRepository;
    }

    public List<Loan> getAll(){
        return loanRepository.findAll();
    }
    public Loan getOne(long loanId){
        return loanRepository.findById(loanId).orElseThrow(()-> new RuntimeException("Loan not found"));
    }
    public Loan create(Loan loan){
        return loanRepository.save(loan);
    }
    public void delete(long loanId){
        if(!loanRepository.existsById(loanId)){
            throw new RuntimeException("Book not found");
        }
        loanRepository.deleteById(loanId);
    }

}
