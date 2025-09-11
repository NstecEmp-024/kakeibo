package com.example.kakeibo.Repository;

import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

import com.example.kakeibo.Entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findBySessionId(String sessionId);
    Optional<Budget> findByCategory(com.example.kakeibo.Entity.Category category);

}
