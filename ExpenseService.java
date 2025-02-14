package com.example.demo.service;

import com.example.demo.model.Expense;
import com.example.demo.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExpenseService {

    @Autowired
    private ExpenseRepository repository;

    public Expense addExpense(Expense expense) {
        return repository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return repository.findAll();
    }

    public Optional<Expense> getExpenseById(Long id) {
        return repository.findById(id);
    }

    public Expense updateExpense(Long id, Expense expense) {
        if (repository.existsById(id)) {
            expense.setId(id);
            return repository.save(expense);
        }
        throw new RuntimeException("Expense not found with id: " + id);
    }

    public void deleteExpense(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Expense not found with id: " + id);
        }
    }
}
