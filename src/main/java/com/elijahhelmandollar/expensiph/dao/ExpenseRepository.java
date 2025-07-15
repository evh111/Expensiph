package com.elijahhelmandollar.expensiph.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.elijahhelmandollar.expensiph.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUser_Username(String username);

    @Query("SELECT e FROM Expense e WHERE e.user.username = :username AND " +
            "(LOWER(e.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(e.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Expense> searchExpenses(@Param("username") String username, @Param("keyword") String keyword);

    @Query("SELECT SUM(e.total) FROM Expense e WHERE e.user.username = :username")
    Double getExpenseTotalByUsername(@Param("username") String username);

    @Query("SELECT e.title, e.total, e.description, e.purchaseDate FROM Expense e " +
            "WHERE e.user.username = :username ORDER BY e.total DESC")
    List<Object[]> getExpenseReportByUsername(@Param("username") String username);

}