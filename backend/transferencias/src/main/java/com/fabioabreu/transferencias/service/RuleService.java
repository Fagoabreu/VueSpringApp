package com.fabioabreu.transferencias.service;

import com.fabioabreu.transferencias.dto.RuleDTO;

import java.util.List;

public interface RuleService {
    RuleDTO addRule(RuleDTO ruleDTO);

    List<RuleDTO> allRules();

    RuleDTO getRuleDetail(Long id);

    RuleDTO updateRule(Long id, RuleDTO ruleDTO);

    void deleteRule(Long id);
}
