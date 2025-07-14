package com.elijahhelmandollar.expensiph.bootstrap;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import com.elijahhelmandollar.expensiph.entity.User;
import com.elijahhelmandollar.expensiph.entity.Expense;
import com.elijahhelmandollar.expensiph.dao.UserRepository;
import com.elijahhelmandollar.expensiph.dao.ExpenseRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class BootStrapData implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;

    public BootStrapData(PasswordEncoder passwordEncoder, UserRepository userRepository, ExpenseRepository expenseRepository) {

        this.passwordEncoder = passwordEncoder;

        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;

    }

    @Override
    public void run(String... args) throws Exception {

        // Create a test user if not already created.
        User user = new User();
        List<Expense> expenses = new ArrayList<>();

        if (userRepository.count() <= 0) {

            user.setUsername("testuser");
            user.setPassword(passwordEncoder.encode("password"));

            userRepository.save(user);

        }

        // Only reload testing expenses if their total count is zero.
        if (expenseRepository.count() <= 0) {

            for (int i = 1; i <= 10; i++) {

                // Create a new "Expense".
                Expense expense = new Expense();

                expense.setTitle("Expense " + i);
                expense.setDescription("Description " + i);
                expense.setTotal(i);
                expense.setPurchaseDate(LocalDate.now().plusDays(365));
                expense.setUser(user);

                expenses.add(expense);
                expenseRepository.save(expense);

            }

        }

        // Associate expenses with the test user and save.
        user.setExpenses(expenses);

    }

}