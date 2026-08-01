package com.maruti.expensetracker.service;

import com.maruti.expensetracker.model.Expense;
import com.maruti.expensetracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    // Add Expense
    public Expense addExpense(Expense expense) {

        return repository.save(expense);
    }

    // Get All Expenses
    public List<Expense> getAllExpenses() {

        return repository.findAll();
    }

    // Get Expense By Id
    public Expense getExpenseById(Long id) {

        Expense expense = repository.findById(id);

        if (expense == null) {
            throw new RuntimeException("Expense not found with id : " + id);
        }

        return expense;
    }

    // Filter By Category
    public List<Expense> getExpenseByCategory(String category) {

        return repository.findByCategory(category);
    }

    // Calculate Total Expense
    public double getTotalExpense() {

        double total = 0;

        List<Expense> expenses = repository.findAll();

        for (Expense expense : expenses) {

            total += expense.getAmount();

        }

        return total;
    }

    // Calculate Total By Category
    public double getCategoryTotal(String category) {

        double total = 0;

        List<Expense> expenses = repository.findByCategory(category);

        for (Expense expense : expenses) {

            total += expense.getAmount();

        }

        return total;
    }

    // Delete Expense
    public boolean deleteExpense(Long id) {

        Expense expense = repository.findById(id);

        if (expense == null) {
            throw new RuntimeException("Expense not found with id : " + id);
        }

        return repository.deleteById(id);
    }

}