package com.example.kakeibo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    // メニュー画面
    @GetMapping("/admin/menu")
    public String menu() {
        return "menu";
    }

    // カテゴリー追加画面
    @GetMapping("/admin/category/category-add")
    public String categoryAdd() {
        return "category-add";
    }

    // カテゴリーリスト画面
    @GetMapping("/admin/category/list")
    public String categoryList() {
        return "category-list";
    }

    // 予算追加画面（カテゴリー選択後）
    @GetMapping("/admin/budget/set-budget")
    public String setBudget() {
        return "set-budget";
    }

    // 予算完了画面
    @GetMapping("/admin/budget/check-budget")
    public String checkBudget() {
        return "check-budget";
    }

    // 支出登録画面
    @GetMapping("/admin/expense/expense-add")
    public String expenseAdd() {
        return "expense-add";
    }

    // 支出完了画面
    @GetMapping("/admin/expense/complete")
    public String expenseComplete() {
        return "expense-complete";
    }

    // 支出リスト画面
    @GetMapping("/admin/expense/list")
    public String expenseList() {
        return "expense-list";
    }

    // 支出削除画面
    @GetMapping("/admin/expense/delete")
    public String expenseDelete() {
        return "expense-delete";
    }

    // 支出編集画面
    @GetMapping("/admin/expense/update")
    public String expenseUpdate() {
        return "expense-update";
    }
}
