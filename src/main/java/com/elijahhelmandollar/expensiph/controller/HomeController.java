package com.elijahhelmandollar.expensiph.controller;

import java.util.List;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import com.elijahhelmandollar.expensiph.entity.Expense;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.elijahhelmandollar.expensiph.dao.ExpenseRepository;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class HomeController {

    private final ExpenseRepository expenseRepository;

    public HomeController(ExpenseRepository expenseRepository) {

        this.expenseRepository = expenseRepository;

    }

    @GetMapping("/")
    public String index(Model model) {

        // Query for the current user.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Obtain a list of all expenses for the given user.
        List<Expense> expenses = expenseRepository.findByUser_Username(username);

        // Pass the list of expenses to the front end.
        model.addAttribute("expenses", expenses);

        return "index";

    }

    @GetMapping("/search")
    public String searchExpenses(@RequestParam("keyword") String keyword, Model model) {

        // Query for the current user.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<Expense> expenses = expenseRepository.searchExpenses(username, keyword);

        if (expenses.isEmpty()) {

            model.addAttribute("notfound", "No results were found.");

        } else {

            model.addAttribute("expenses", expenses);
            model.addAttribute("keyword", keyword);

        }

        return "index";

    }

}