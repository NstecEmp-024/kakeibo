package com.example.kakeibo.controller;

import com.example.kakeibo.Entity.Category;
import com.example.kakeibo.Repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // カテゴリー一覧表示
    // @GetMapping("/admin/category/list")
    // public String categoryList(Model model) {
    //     List<Category> categories = categoryRepository.findAll();
    //     model.addAttribute("categories", categories);
    //     return "admin/category/list"; // templates/admin/category/list.html
    // }
}
