package com.example.kakeibo.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sessionId; // セッションIDで管理

    private Integer amount;

    // Category と 1対1で紐付け
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, unique = true)
    private Category category;
}
