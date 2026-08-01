package com.maruti.expensetracker;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.maruti.expensetracker.model.Expense;
import com.maruti.expensetracker.repository.ExpenseRepository;
import com.maruti.expensetracker.service.ExpenseService;

class ExpenseServiceTest {

    private ExpenseRepository repository;
    private ExpenseService service;

    @BeforeEach
    void setUp() {

        repository = Mockito.mock(ExpenseRepository.class);
        service = new ExpenseService(repository);

    }

    @Test
    void testAddExpense() {

        Expense expense = new Expense(
                1L,
                "Pizza",
                350,
                "Food",
                LocalDate.now()
        );

        when(repository.save(expense)).thenReturn(expense);

        Expense result = service.addExpense(expense);

        assertNotNull(result);
        assertEquals("Pizza", result.getTitle());

    }

    @Test
    void testGetAllExpenses() {

        List<Expense> list = Arrays.asList(

                new Expense(1L,"Pizza",350,"Food",LocalDate.now()),
                new Expense(2L,"Petrol",1200,"Travel",LocalDate.now())

        );

        when(repository.findAll()).thenReturn(list);

        List<Expense> result = service.getAllExpenses();

        assertEquals(2,result.size());

    }

    @Test
    void testTotalExpense(){

        List<Expense> list = Arrays.asList(

                new Expense(1L,"Pizza",350,"Food",LocalDate.now()),
                new Expense(2L,"Petrol",1200,"Travel",LocalDate.now())

        );

        when(repository.findAll()).thenReturn(list);

        double total = service.getTotalExpense();

        assertEquals(1550,total);

    }

    @Test
    void testDeleteExpense(){

        Expense expense =
                new Expense(1L,"Pizza",350,"Food",LocalDate.now());

        when(repository.findById(1L)).thenReturn(expense);

        when(repository.deleteById(1L)).thenReturn(true);

        assertTrue(service.deleteExpense(1L));

    }

}