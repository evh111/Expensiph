package com.elijahhelmandollar.expensiph.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.elijahhelmandollar.expensiph.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUser_Username(String username);

    @Query("SELECT e FROM Expense e WHERE e.user.username = :username AND " +
            "(LOWER(e.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(e.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Expense> searchExpenses(@Param("username") String username, @Param("keyword") String keyword);

}