package com.example.kakeibo.Repository;

import java.util.List;
import com.example.kakeibo.Entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findBySessionId(String sessionId);
}
