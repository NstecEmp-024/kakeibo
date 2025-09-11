package com.example.kakeibo.controller;

import com.example.kakeibo.Entity.Budget;
import com.example.kakeibo.Repository.BudgetRepository;
import com.example.kakeibo.Repository.CategoryRepository;
import com.example.kakeibo.service.BudgetsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/budgets")
public class BudgetsController {

    private final BudgetsService budgetsService;
    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;

    public BudgetsController(BudgetsService budgetsService,
            BudgetRepository budgetRepository,
            CategoryRepository categoryRepository) {
        this.budgetsService = budgetsService;
        this.budgetRepository = budgetRepository;
        this.categoryRepository = categoryRepository;
    }

    // ===== 管理画面用 =====

    // 更新処理のみ残す
    @PostMapping("/update")
    public String updateBudget(@ModelAttribute Budget budget, HttpSession session) {
        budget.setSessionId(session.getId());
        budgetRepository.save(budget);
        return "redirect:/admin/budget/check-budget";
    }

    // ===== REST API (JSON) =====

    @GetMapping("/api")
    @ResponseBody
    public List<Budget> getAllBudgets(HttpSession session) {
        return budgetsService.findBySessionId(session.getId());
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public Optional<Budget> getBudgetById(@PathVariable Long id) {
        return budgetsService.findById(id);
    }

    @PostMapping("/api")
    @ResponseBody
    public Budget createBudget(@RequestBody Budget budget, HttpSession session) {
        budget.setSessionId(session.getId());
        return budgetsService.save(budget);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void deleteBudget(@PathVariable Long id) {
        budgetsService.deleteById(id);
    }
}
