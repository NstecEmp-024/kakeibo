package com.example.kakeibo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kakeibo.Entity.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    // 必要ならカスタムメソッドをここに追加
    List<Budget> findByUserId(Long userId);
    List<Budget> findByCategory(String category);

}
