package com.fabioabreu.transferencias.repository;

import com.fabioabreu.transferencias.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<TransferEntity,Long> {
}
