package com.fabioabreu.transferencias.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="Transfer")
@Data
@NoArgsConstructor
public class TransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String originAccount;
    private String destinyAccount;
    private double transferValue;
    private double taxValue;
    private Date transferDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
