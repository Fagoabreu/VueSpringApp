package com.fabioabreu.transferencias.service;

import com.fabioabreu.transferencias.dto.RuleDTO;

import java.util.List;
import java.util.Optional;

public interface RuleService {
    RuleDTO addRule(RuleDTO ruleDTO);

    List<RuleDTO> allRules(Optional<Integer> days);

    RuleDTO getRuleDetail(Long id);

    RuleDTO updateRule(Long id, RuleDTO ruleDTO);

    void deleteRule(Long id);
}
