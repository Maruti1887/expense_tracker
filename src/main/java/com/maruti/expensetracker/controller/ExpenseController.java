package com.maruti.expensetracker.controller;

import com.maruti.expensetracker.model.Expense;
import com.maruti.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Expense API",
        description = "Operations related to Expense Management"
)
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @Operation(summary = "Add New Expense")
    @PostMapping
    public ResponseEntity<Expense> addExpense(@Valid @RequestBody Expense expense) {

        Expense savedExpense = service.addExpense(expense);

        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Expenses")
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {

        return ResponseEntity.ok(service.getAllExpenses());
    }

    @Operation(summary = "Get Expense By Id")
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {

        return ResponseEntity.ok(service.getExpenseById(id));
    }

    @Operation(summary = "Filter Expenses By Category")
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Expense>> getByCategory(
            @PathVariable String category) {

        return ResponseEntity.ok(service.getExpenseByCategory(category));
    }

    @Operation(summary = "Calculate Total Expense")
    @GetMapping("/total")
    public ResponseEntity<Map<String, Double>> getTotalExpense() {

        return ResponseEntity.ok(
                Map.of("total", service.getTotalExpense())
        );
    }

    @Operation(summary = "Calculate Category Total")
    @GetMapping("/total/{category}")
    public ResponseEntity<Map<String, Object>> getCategoryTotal(
            @PathVariable String category) {

        return ResponseEntity.ok(
                Map.of(
                        "category", category,
                        "total", service.getCategoryTotal(category)
                )
        );
    }

    @Operation(summary = "Delete Expense")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(
            @PathVariable Long id) {

        service.deleteExpense(id);

        return ResponseEntity.ok("Expense deleted successfully.");
    }

}