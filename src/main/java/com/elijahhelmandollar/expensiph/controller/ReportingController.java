package com.elijahhelmandollar.expensiph.controller;

import java.util.List;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import com.elijahhelmandollar.expensiph.service.ReportingService;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class ReportingController {

    private final ReportingService reportingService;

    public ReportingController(ReportingService reportingService) {

        this.reportingService = reportingService;

    }

    @GetMapping("/reporting")
    public String showReporting(Model model) {

        // Query for the current user.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Double totalExpenses = reportingService.getExpenseTotal(username);
        List<Object[]> expenseReport = reportingService.getExpenseReportByUsername(username);

        model.addAttribute("totalExpenses", totalExpenses != null ? totalExpenses : 0);

        if (!expenseReport.isEmpty()) {

            model.addAttribute("monthlyExpenses", expenseReport);

        } else {

            model.addAttribute("notfound", "There are no expenses to report.");

        }

        return "reporting";

    }

}
