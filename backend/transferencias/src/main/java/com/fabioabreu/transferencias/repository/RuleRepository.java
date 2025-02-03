package com.fabioabreu.transferencias.repository;

import com.fabioabreu.transferencias.entity.RuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RuleRepository extends JpaRepository<RuleEntity,Long> {
    List<RuleEntity> findAllByDayMinGreaterThanEqualAndDayMaxLessThanEqual(int lesser, int bigger);
}
