package com.elijahhelmandollar.expensiph.bootstrap;

import java.time.LocalDate;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import com.elijahhelmandollar.expensiph.entity.Expense;
import com.elijahhelmandollar.expensiph.dao.ExpenseRepository;

@Component
public class BootStrapData implements CommandLineRunner {

    private final ExpenseRepository expenseRepository;

    public BootStrapData(ExpenseRepository expenseRepository) {

        this.expenseRepository = expenseRepository;

    }

    @Override
    public void run(String... args) throws Exception {

        long currentExpenses = expenseRepository.count();

        // Only reload testing expenses if their count is zero.
        if (currentExpenses <= 0) {

            for (int i = 1; i <= 10; i++) {

                // Create a new "Expense".
                Expense expense = new Expense();

                expense.setTitle("Expense " + i);
                expense.setDescription("Description " + i);
                expense.setTotal(i);
                expense.setPurchaseDate(LocalDate.now().plusDays(365));

                expenseRepository.save(expense);

            }

            expenseRepository.findAll().forEach(System.out::println);

        }

    }

}