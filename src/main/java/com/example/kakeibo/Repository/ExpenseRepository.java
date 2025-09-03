package com.example.kakeibo.Repository;

import java.util.List;
import com.example.kakeibo.Entity.Expense;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // 必要ならカスタムメソッドをここに追加
    List<Expense> findByUserId(Long userId);
    List<Expense> findByCategory(String category);

}
