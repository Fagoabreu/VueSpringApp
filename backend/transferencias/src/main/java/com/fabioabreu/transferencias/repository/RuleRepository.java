package com.fabioabreu.transferencias.repository;

import com.fabioabreu.transferencias.entity.RuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<RuleEntity,Long> {
}
