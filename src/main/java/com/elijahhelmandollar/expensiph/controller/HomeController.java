package com.elijahhelmandollar.expensiph.controller;

import java.util.List;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import com.elijahhelmandollar.expensiph.entity.Expense;
import org.springframework.web.bind.annotation.GetMapping;
import com.elijahhelmandollar.expensiph.dao.ExpenseRepository;

@Controller
public class HomeController {

    private final ExpenseRepository expenseRepository;

    public HomeController(ExpenseRepository expenseRepository) {

        this.expenseRepository = expenseRepository;

    }

    @GetMapping("/")
    public String index(Model model) {

        // Obtain a list of all expenses.
        List<Expense> expenses = expenseRepository.findAll();

        // Pass the list of expenses to the front end.
        model.addAttribute("expenses", expenses);

        return "index";

    }

}