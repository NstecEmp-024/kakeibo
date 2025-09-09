package com.example.kakeibo.service;

import com.example.kakeibo.Entity.Expense;
import com.example.kakeibo.Repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseService expenseService;

    private Expense expense;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        expense = new Expense(1L, "session123", "Food", 1200, "Lunch");
    }

    @Test
    void testFindAll() {
        when(expenseRepository.findAll()).thenReturn(Arrays.asList(expense));

        List<Expense> expenses = expenseService.findAll();

        assertEquals(1, expenses.size());
        assertEquals("Food", expenses.get(0).getCategory());
        verify(expenseRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(expense));

        Optional<Expense> result = expenseService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Lunch", result.get().getMemo());
        verify(expenseRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        when(expenseRepository.save(expense)).thenReturn(expense);

        Expense saved = expenseService.save(expense);

        assertNotNull(saved);
        assertEquals("Food", saved.getCategory());
        verify(expenseRepository, times(1)).save(expense);
    }

    @Test
    void testDeleteById() {
        expenseService.deleteById(1L);

        verify(expenseRepository, times(1)).deleteById(1L);
    }
}
