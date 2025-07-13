package com.elijahhelmandollar.expensiph.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.elijahhelmandollar.expensiph.entity.Expense;
import com.elijahhelmandollar.expensiph.dao.ExpenseRepository;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    // Designate utilized repository classes.
    private final ExpenseRepository expenseRepository;

    public ExpenseController(ExpenseRepository expenseRepository) {

        this.expenseRepository = expenseRepository;

    }

    @GetMapping("/")
    public List<Expense> getExpenses() {

        return expenseRepository.findAll();

    }

    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable("id") Long id) {

        return expenseRepository.findById(id).orElse(null);

    }

    @PostMapping("/create/")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {

        Expense createdExpense = expenseRepository.save(expense);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpenseById(@PathVariable("id") Long id) {

        if (!expenseRepository.existsById(id)) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense " + id + " was not found.");

        }

        expenseRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Expense " + id + " was deleted successfully.");

    }

}