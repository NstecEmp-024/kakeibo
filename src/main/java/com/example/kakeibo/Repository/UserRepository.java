package com.example.kakeibo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.kakeibo.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // 必要ならカスタムメソッドをここに追加
}
