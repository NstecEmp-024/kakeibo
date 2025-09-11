package com.example.kakeibo.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryBudgetForm {
    private String name;   // カテゴリー名
    private Integer amount; // 予算額
}
