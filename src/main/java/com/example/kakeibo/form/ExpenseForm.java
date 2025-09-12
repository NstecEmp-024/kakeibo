package com.example.kakeibo.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ExpenseForm {

    @NotNull(message = "支出名は必須です")
    private String name;

    @NotNull(message = "金額は必須です")
    @Positive(message = "金額は正の値でなければなりません")
    private Integer amount;

    @NotNull(message = "カテゴリを選択してください")
    private Long categoryId;

    // getter / setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
}
