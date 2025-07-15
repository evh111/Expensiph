package com.elijahhelmandollar.expensiph.controller;

import java.security.Principal;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import com.elijahhelmandollar.expensiph.entity.User;
import com.elijahhelmandollar.expensiph.entity.Expense;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.elijahhelmandollar.expensiph.dao.UserRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.elijahhelmandollar.expensiph.dao.ExpenseRepository;

@Controller
public class ExpenseFormController {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseFormController(ExpenseRepository expenseRepository, UserRepository userRepository) {

        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    // Serve the create expense form.
    @GetMapping("/create")
    public String createExpenseForm(Model model) {

        model.addAttribute("expense", new Expense());

        return "create_expense";

    }

    // Process the creation form's submission.
    @PostMapping("/create")
    public String createExpense(@ModelAttribute Expense expense, Principal principal) {

        // Query the logged-in user.
        String username = principal.getName();
        User user = userRepository.findByUsername(username);

        expense.setUser(user);
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
    public String updateExpense(@ModelAttribute Expense expense, Principal principal) {

        // Query the logged-in user.
        String username = principal.getName();
        User user = userRepository.findByUsername(username);

        if (expenseRepository.existsById(expense.getId())) {

            expense.setUser(user);
            expenseRepository.save(expense);

        }

        return "redirect:/";

    }

}
