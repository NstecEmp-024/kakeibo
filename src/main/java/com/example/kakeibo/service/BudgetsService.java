package com.example.kakeibo.service;

import com.example.kakeibo.Entity.Budget;
import com.example.kakeibo.Repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetsService {

    private final BudgetRepository budgetsRepository;

    public BudgetsService(BudgetRepository budgetsRepository) {
        this.budgetsRepository = budgetsRepository;
    }

    public List<Budget> findAll() {
        return budgetsRepository.findAll();
    }

    public Optional<Budget> findById(Long id) {
        return budgetsRepository.findById(id);
    }

    public Budget save(Budget budget) {
        return budgetsRepository.save(budget);
    }

    public void deleteById(Long id) {
        budgetsRepository.deleteById(id);
    }
}
