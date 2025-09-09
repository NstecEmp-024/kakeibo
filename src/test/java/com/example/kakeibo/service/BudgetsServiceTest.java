package com.example.kakeibo.service;

import com.example.kakeibo.Entity.Budget;
import com.example.kakeibo.Repository.BudgetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetsServiceTest {

    private BudgetsService budgetsService;
    private BudgetRepository budgetsRepository;

    @BeforeEach
    public void setUp() {
        budgetsRepository = Mockito.mock(BudgetRepository.class);
        budgetsService = new BudgetsService(budgetsRepository);
    }

    @Test
    public void testFindAll() {
        Budget b1 = new Budget(1L, "session1", "Food", 5000);
        Budget b2 = new Budget(2L, "session1", "Transport", 2000);

        Mockito.when(budgetsRepository.findAll()).thenReturn(Arrays.asList(b1, b2));

        List<Budget> result = budgetsService.findAll();
        assertEquals(2, result.size());

        System.out.println("%TESTC 0 BudgetsServiceTest.testFindAll");
        System.out.println("%RUNTIME0");
    }

    @Test
    public void testSave() {
        Budget budget = new Budget(null, "session2", "Entertainment", 3000);
        Budget savedBudget = new Budget(1L, "session2", "Entertainment", 3000);

        Mockito.when(budgetsRepository.save(budget)).thenReturn(savedBudget);

        Budget result = budgetsService.save(budget);
        assertNotNull(result.getId());
        assertEquals("Entertainment", result.getCategory());

        System.out.println("%TESTC 1 BudgetsServiceTest.testSave");
        System.out.println("%RUNTIME0");
    }

    @Test
    public void testFindById() {
        Budget budget = new Budget(1L, "session1", "Food", 5000);
        Mockito.when(budgetsRepository.findById(1L)).thenReturn(Optional.of(budget));

        Optional<Budget> result = budgetsService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Food", result.get().getCategory());

        System.out.println("%TESTC 2 BudgetsServiceTest.testFindById");
        System.out.println("%RUNTIME0");
    }

    @Test
    public void testDeleteById() {
        Mockito.doNothing().when(budgetsRepository).deleteById(1L);

        budgetsService.deleteById(1L);

        Mockito.verify(budgetsRepository, Mockito.times(1)).deleteById(1L);

        System.out.println("%TESTC 3 BudgetsServiceTest.testDeleteById");
        System.out.println("%RUNTIME0");
    }

    @Test
    public void testFindBySessionId() {
        Budget b1 = new Budget(1L, "session1", "Food", 5000);
        Mockito.when(budgetsRepository.findBySessionId("session1")).thenReturn(Arrays.asList(b1));

        List<Budget> result = budgetsService.findBySessionId("session1");
        assertEquals(1, result.size());
        assertEquals("Food", result.get(0).getCategory());

        System.out.println("%TESTC 4 BudgetsServiceTest.testFindBySessionId");
        System.out.println("%RUNTIME0");
    }
}
