package com.example.kakeibo.Repository;

import java.util.List;
import com.example.kakeibo.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findBySessionId(String sessionId);
}
