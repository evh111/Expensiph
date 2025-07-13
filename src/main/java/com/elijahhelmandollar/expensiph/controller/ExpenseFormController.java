package com.elijahhelmandollar.expensiph.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import com.elijahhelmandollar.expensiph.entity.Expense;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.elijahhelmandollar.expensiph.dao.ExpenseRepository;

@Controller
public class ExpenseFormController {

    private final ExpenseRepository expenseRepository;

    public ExpenseFormController(ExpenseRepository expenseRepository) {

        this.expenseRepository = expenseRepository;

    }

    // Serve the create expense form.
    @GetMapping("/create")
    public String createExpenseForm(Model model) {

        model.addAttribute("expense", new Expense());

        return "create_expense";

    }

    // Process the creation form's submission.
    @PostMapping("/create")
    public String createExpense(@ModelAttribute Expense expense) {

        expenseRepository.save(expense);

        return "redirect:/";

    }

    // Serve the update expense form.
    @GetMapping("/expense/{id}")
    public String updateExpenseForm(@PathVariable("id") Long id, Model model) {

        Expense expense = expenseRepository.findById(id).orElse(null);

        if (expense == null) {

            return "redirect:/";

        }

        model.addAttribute("expense", expense);

        return "expense_details";

    }

    // Process the update form's submission.
    @PostMapping("/update")
    public String updateExpense(@ModelAttribute Expense expense) {

        if (expenseRepository.existsById(expense.getId())) {

            expenseRepository.save(expense);

        }

        return "redirect:/";

    }

}
