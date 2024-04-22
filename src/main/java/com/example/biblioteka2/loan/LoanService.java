package com.example.biblioteka2.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serwis obsługujący operacje na wypożyczeniach książek w systemie.
 */
@Service
public class LoanService {

    private LoanRepository loanRepository;

    /**
     * Konstruktor serwisu wypożyczeń książek.
     * @param loanRepository repozytorium wypożyczeń książek
     */
    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    /**
     * Pobiera wszystkie wypożyczenia książek z systemu.
     * @return lista wszystkich wypożyczeń książek
     */
    public List<Loan> getAll(){
        return loanRepository.findAll();
    }

    /**
     * Pobiera pojedyncze wypożyczenie na podstawie jego identyfikatora.
     * @param loanId identyfikator wypożyczenia
     * @return wypożyczenie o podanym identyfikatorze
     * @throws RuntimeException jeśli wypożyczenie nie zostanie znalezione
     */
    public Loan getOne(long loanId){
        return loanRepository.findById(loanId).orElseThrow(()-> new RuntimeException("Loan not found"));
    }

    /**
     * Tworzy nowe wypożyczenie książki w systemie.
     * @param loan dane nowego wypożyczenia
     * @return nowo utworzone wypożyczenie
     */
    public Loan create(Loan loan){
        return loanRepository.save(loan);
    }

    /**
     * Usuwa wypożyczenie książki z systemu na podstawie jego identyfikatora.
     * @param loanId identyfikator wypożyczenia do usunięcia
     * @throws RuntimeException jeśli wypożyczenie nie zostanie znalezione
     */
    public void delete(long loanId){
        if(!loanRepository.existsById(loanId)){
            throw new RuntimeException("Loan not found");
        }
        loanRepository.deleteById(loanId);
    }

}
