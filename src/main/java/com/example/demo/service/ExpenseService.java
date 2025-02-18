package com.example.demo.service;

import com.example.demo.model.Expenses;
import com.example.demo.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expenses> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Cacheable(value = "expenses", key = "#id")
    public Expenses getExpenseById(String id) {
        Optional<Expenses> expense = expenseRepository.findById(id);
        return expense.orElse(null);
    }

    @CacheEvict(value = "expenses", allEntries = true)
    public Expenses createExpense(Expenses expense) {
        return expenseRepository.save(expense);
    }

    @CacheEvict(value = "expenses", key = "#id")
    public Expenses updateExpense(String id, Expenses expense) {
        expense.setId(id);
        return expenseRepository.save(expense);
    }

    @CacheEvict(value = "expenses", key = "#id")
    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }
}


