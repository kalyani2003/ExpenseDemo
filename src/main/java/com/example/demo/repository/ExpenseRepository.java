package com.example.demo.repository;

import com.example.demo.model.Expenses;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ExpenseRepository extends MongoRepository<Expenses, String> {

    // Custom query to find expenses by category
    List<Expenses> findByCategory(String category);

    // Custom query to find expenses by name (if needed)
    List<Expenses> findByName(String name);
}
