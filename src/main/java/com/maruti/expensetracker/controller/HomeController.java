package com.maruti.expensetracker.controller;

import com.maruti.expensetracker.model.Expense;
import com.maruti.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private final ExpenseService service;

    public HomeController(ExpenseService service) {
        this.service = service;
    }

    // Dashboard
    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("expense", new Expense());
        model.addAttribute("expenses", service.getAllExpenses());
        model.addAttribute("totalExpense", service.getTotalExpense());

        return "index";
    }

    // Save Expense
    @PostMapping("/save")
    public String saveExpense(@Valid @ModelAttribute("expense") Expense expense,
                              BindingResult result,
                              Model model) {

        if (result.hasErrors()) {

            model.addAttribute("expenses", service.getAllExpenses());
            model.addAttribute("totalExpense", service.getTotalExpense());

            return "index";
        }

        service.addExpense(expense);

        return "redirect:/";
    }

    // Delete Expense
    @GetMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {

        service.deleteExpense(id);

        return "redirect:/";
    }

    // Filter Category
    @GetMapping("/category")
    public String filterCategory(@RequestParam String category,
                                 Model model) {

        model.addAttribute("expense", new Expense());
        model.addAttribute("expenses",
                service.getExpenseByCategory(category));
        model.addAttribute("totalExpense",
                service.getCategoryTotal(category));
        model.addAttribute("selectedCategory", category);

        return "index";
    }

    // Show All
    @GetMapping("/all")
    public String showAll(Model model) {

        model.addAttribute("expense", new Expense());
        model.addAttribute("expenses", service.getAllExpenses());
        model.addAttribute("totalExpense", service.getTotalExpense());

        return "index";
    }

}