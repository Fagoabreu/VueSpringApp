package com.fabioabreu.transferencias.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="Rule")
@Data
@NoArgsConstructor
public class RuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int dayMin;
    private int dayMax;
    private double rate;
    private float taxPct;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
