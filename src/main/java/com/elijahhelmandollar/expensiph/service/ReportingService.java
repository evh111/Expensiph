package com.elijahhelmandollar.expensiph.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.elijahhelmandollar.expensiph.dao.ExpenseRepository;

@Service
public class ReportingService {

    private final ExpenseRepository expenseRepository;

    public ReportingService(ExpenseRepository expenseRepository) {

        this.expenseRepository = expenseRepository;

    }

    public Double getExpenseTotal(String username) {

        return expenseRepository.getExpenseTotalByUsername(username);

    }

    public List<Object[]> getExpenseReportByUsername(String username) {

        return expenseRepository.getExpenseReportByUsername(username);

    }

}
