package com.example.kakeibo.service;

import com.example.kakeibo.Entity.Expense;
import com.example.kakeibo.Repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expensesRepository) {
        this.expenseRepository = expensesRepository;
    }

    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> findById(Integer id) {
        return expenseRepository.findById(id);
    }

    public Expense save(Expense expenses) {
        return expenseRepository.save(expenses);
    }

    public void deleteById(Integer id) {
        expenseRepository.deleteById(id);
    }

    public List<Expense> findByUserId(Integer userId) {
        return expenseRepository.findByUserId(userId);
    }
}
