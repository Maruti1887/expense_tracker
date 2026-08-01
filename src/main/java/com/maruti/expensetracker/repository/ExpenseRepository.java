package com.maruti.expensetracker.repository;

import com.maruti.expensetracker.model.Expense;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ExpenseRepository {

    private final List<Expense> expenseList = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // Save Expense
    public Expense save(Expense expense) {

        expense.setId(idGenerator.getAndIncrement());
        expenseList.add(expense);

        return expense;
    }

    // Get All Expenses
    public List<Expense> findAll() {
        return expenseList;
    }

    // Get Expense By Id
    public Expense findById(Long id) {

        for (Expense expense : expenseList) {

            if (expense.getId().equals(id)) {
                return expense;
            }

        }

        return null;
    }

    // Delete Expense
    public boolean deleteById(Long id) {

        return expenseList.removeIf(expense ->
                expense.getId().equals(id));
    }

    // Find By Category
    public List<Expense> findByCategory(String category) {

        List<Expense> list = new ArrayList<>();

        for (Expense expense : expenseList) {

            if (expense.getCategory().equalsIgnoreCase(category)) {
                list.add(expense);
            }

        }

        return list;
    }

}