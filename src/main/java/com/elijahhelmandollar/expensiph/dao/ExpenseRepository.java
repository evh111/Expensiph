package com.elijahhelmandollar.expensiph.dao;

import com.elijahhelmandollar.expensiph.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {}