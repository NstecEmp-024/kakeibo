package com.example.kakeibo.controller;

import com.example.kakeibo.Entity.Budget;
import com.example.kakeibo.Entity.Category;
import com.example.kakeibo.Repository.BudgetRepository;
import com.example.kakeibo.Repository.CategoryRepository;
import com.example.kakeibo.form.CategoryBudgetForm;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;

    public AdminController(BudgetRepository budgetRepository, CategoryRepository categoryRepository) {
        this.budgetRepository = budgetRepository;
        this.categoryRepository = categoryRepository;
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

        // Category を作成
        Category category = new Category();
        category.setName(form.getName());
        categoryRepository.save(category);

        // Budget を作成
        Budget budget = new Budget();
        budget.setCategory(category);
        budget.setAmount(form.getAmount());
        budgetRepository.save(budget);

        // 完了画面に表示
        model.addAttribute("category", category);
        model.addAttribute("budget", budget);

        return "admin/category/complete";
    }

    // カテゴリーと予算の追加処理
    // @PostMapping("/admin/category/category-add")
    // public String saveCategoryWithBudget(@ModelAttribute Category category,
    // @RequestParam("amount") Integer amount,
    // Model model) {
    // // カテゴリー保存
    // categoryRepository.save(category);

    // // 予算保存
    // Budget budget = new Budget();
    // budget.setCategory(category);
    // budget.setAmount(amount);
    // budgetRepository.save(budget);

    // model.addAttribute("category", category);
    // return "admin/category/complete"; // 追加完了画面に遷移
    // }

    // // カテゴリー追加画面
    // @GetMapping("/admin/category/category-add")
    // public String categoryAdd(Model model) {
    // model.addAttribute("category", new Category());
    // return "admin/category/category-add";
    // }

    // // カテゴリー追加処理
    // @PostMapping("/admin/category/category-add")
    // public String saveCategory(@ModelAttribute Category category, Model model) {
    // categoryRepository.save(category);
    // model.addAttribute("category", category);
    // return "admin/category/complete"; // 追加完了画面に遷移
    // }

    // // カテゴリー追加完了画面
    // @GetMapping("/admin/category/complete")
    // public String categoryComplete() {
    // return "admin/category/complete";
    // }

    // カテゴリーリスト画面
    // @GetMapping("/admin/category/list")
    // public String categoryList(Model model) {
    // model.addAttribute("categories", categoryRepository.findAll());
    // return "admin/category/category-list";
    // }

    // 予算編集画面表示

    // 予算追加画面

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
    @GetMapping("/admin/expense/expense-add")
    public String expenseAdd() {
        return "admin/expense/expense-add";
    }

    // 支出完了画面
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
