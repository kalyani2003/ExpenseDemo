package com.example.demo.controller;

import com.example.demo.model.Expenses;
import com.example.demo.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // GET all expenses
    @GetMapping
    public ResponseEntity<List<Expenses>> getAllExpenses() {
        return new ResponseEntity<>(expenseService.getAllExpenses(), HttpStatus.OK);
    }

    // GET
    @Cacheable(value = "expenses", key = "#id")
    @GetMapping("/{id}")
    public ResponseEntity<Expenses> getExpenseById(@PathVariable String id) {
        return new ResponseEntity<>(expenseService.getExpenseById(id), HttpStatus.OK);
    }

    // POST
    @CacheEvict(value = "expenses", allEntries = true)
    @PostMapping
    public ResponseEntity<Expenses> createExpense(@Valid @RequestBody Expenses expense) {
        return new ResponseEntity<>(expenseService.createExpense(expense), HttpStatus.CREATED);
    }

    // PUT
    @CacheEvict(value = "expenses", key = "#id")
    @PutMapping("/{id}")
    public ResponseEntity<Expenses> updateExpense(@PathVariable String id, @Valid @RequestBody Expenses expense) {
        return new ResponseEntity<>(expenseService.updateExpense(id, expense), HttpStatus.OK);
    }

    // DELETE
    @CacheEvict(value = "expenses", key = "#id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable String id) {
        expenseService.deleteExpense(id);
        return new ResponseEntity<>("Expense deleted successfully", HttpStatus.OK);
    }
}




