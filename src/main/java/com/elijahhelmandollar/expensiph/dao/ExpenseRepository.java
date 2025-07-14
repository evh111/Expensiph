package com.elijahhelmandollar.expensiph.dao;

import com.elijahhelmandollar.expensiph.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUser_Username(String username);

}