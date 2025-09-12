package com.example.kakeibo.controller;

import com.example.kakeibo.Entity.Budget;
import com.example.kakeibo.Entity.Category;
import com.example.kakeibo.Entity.Expense;
import com.example.kakeibo.Repository.BudgetRepository;
import com.example.kakeibo.Repository.CategoryRepository;
import com.example.kakeibo.Repository.ExpenseRepository;
import com.example.kakeibo.form.CategoryBudgetForm;
import com.example.kakeibo.form.ExpenseForm;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;

    public AdminController(BudgetRepository budgetRepository, CategoryRepository categoryRepository,
            ExpenseRepository expenseRepository) {
        this.budgetRepository = budgetRepository;
        this.categoryRepository = categoryRepository;
        this.expenseRepository = expenseRepository;
    }

    // メニュー画面
    @GetMapping("/admin/menu")
    public String menu() {
        return "admin/menu";
    }

    // カテゴリー追加画面
    @GetMapping("/admin/category/category-add")
    public String categoryAdd(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("budget", new Budget());
        return "admin/category/category-add";
    }

    @PostMapping("/admin/category/category-add")
    public String saveCategoryWithBudget(@ModelAttribute CategoryBudgetForm form, Model model) {
        Category category = new Category();
        category.setName(form.getName());
        categoryRepository.save(category);

        Budget budget = new Budget();
        budget.setCategory(category);
        budget.setAmount(form.getAmount());
        budgetRepository.save(budget);

        model.addAttribute("category", category);
        model.addAttribute("budget", budget);

        return "admin/category/complete";
    }

    // カテゴリー一覧
    @GetMapping("/admin/category/list")
    public String categoryList(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/category/list";
    }

    // 予算設定画面（GET）
    @GetMapping("/admin/budget/set-budget/{categoryId}")
    public String setBudget(@PathVariable Long categoryId, Model model) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + categoryId));

        Budget budget = budgetRepository.findByCategory(category).orElse(new Budget());
        budget.setCategory(category);

        model.addAttribute("budget", budget);
        return "admin/budget/set-budget";
    }

    // 予算保存（POST）
    @PostMapping("/admin/budget/set-budget")
    public String saveBudget(@ModelAttribute Budget budget, HttpSession session) {
        budget.setSessionId(session.getId());
        budgetRepository.save(budget);
        return "redirect:/admin/budget/check-budget";
    }

    // 予算完了画面
    @GetMapping("/admin/budget/check-budget")
    public String checkBudget() {
        return "admin/budget/check-budget";
    }

    // 支出登録画面
    // @GetMapping("/admin/expense/expense-add")
    // public String expenseAdd(Model model) {
    // model.addAttribute("expense", new Expense());
    // model.addAttribute("categories", categoryRepository.findAll());
    // return "admin/expense/expense-add";
    // }

    // // 支出登録処理 (POST)
    // @PostMapping("/admin/expense/expense-add")
    // public String saveExpense(@ModelAttribute Expense expense,
    // @RequestParam Long categoryId,
    // HttpSession session) {
    // Category category = categoryRepository.findById(categoryId)
    // .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" +
    // categoryId));

    // expense.setCategory(category);
    // expense.setSessionId(session.getId());

    // expenseRepository.save(expense);

    // // 完了画面にリダイレクト
    // return "redirect:/admin/expense/expense-complete";
    // }

    // // 支出完了画面
    // @GetMapping("/admin/expense/expense-complete")
    // public String expenseComplete() {
    // return "admin/expense/expense-complete";
    // }

    // GET: 登録画面
    @GetMapping("/admin/expense/add")
    public String expenseAdd(Model model) {
        model.addAttribute("expenseForm", new ExpenseForm());
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/expense/expense-add";
    }

    // POST: 登録処理
    @PostMapping("/admin/expense/add")
    public String saveExpense(@Valid @ModelAttribute("expenseForm") ExpenseForm form,
            BindingResult bindingResult,
            HttpSession session,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "admin/expense/expense-add";
        }

        Category category = categoryRepository.findById(form.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id: " + form.getCategoryId()));

        Expense expense = new Expense();
        expense.setName(form.getName());
        expense.setAmount(form.getAmount());
        expense.setCategory(category);
        expense.setSessionId(session.getId());

        expenseRepository.save(expense);

        return "redirect:/admin/expense/complete";
    }

    // 完了画面
    @GetMapping("/admin/expense/complete")
    public String expenseComplete() {
        return "admin/expense/expense-complete";
    }

    // 支出リスト画面
    @GetMapping("/admin/expense/list")
    public String expenseList() {
        return "admin/expense/expense-list";
    }

    // 支出削除画面
    @GetMapping("/admin/expense/delete")
    public String expenseDelete() {
        return "admin/expense/expense-delete";
    }

    // 支出編集画面
    @GetMapping("/admin/expense/update")
    public String expenseUpdate() {
        return "admin/expense/expense-update";
    }
}
