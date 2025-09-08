package com.example.kakeibo.controller;

import com.example.kakeibo.Entity.Budget;
import com.example.kakeibo.service.BudgetsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/budgets")
public class BudgetsController {

    private final BudgetsService budgetsService;

    public BudgetsController(BudgetsService budgetsService) {
        this.budgetsService = budgetsService;
    }

    @GetMapping
    public List<Budget> getAllBudgets() {
        return budgetsService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Budget> getBudgetById(@PathVariable Long id) {
        return budgetsService.findById(id);
    }

    @PostMapping
    public Budget createBudget(@RequestBody Budget budget) {
        return budgetsService.save(budget);
    }

    @DeleteMapping("/{id}")
    public void deleteBudget(@PathVariable Long id) {
        budgetsService.deleteById(id);
    }
}
