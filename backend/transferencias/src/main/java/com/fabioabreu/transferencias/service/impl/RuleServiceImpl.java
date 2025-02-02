package com.fabioabreu.transferencias.service.impl;

import com.fabioabreu.transferencias.converter.RuleConverter;
import com.fabioabreu.transferencias.dto.RuleDTO;
import com.fabioabreu.transferencias.entity.RuleEntity;
import com.fabioabreu.transferencias.repository.RuleRepository;
import com.fabioabreu.transferencias.service.RuleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {

    private final RuleRepository ruleRepository;
    private final RuleConverter ruleConverter;

    public RuleServiceImpl(RuleRepository ruleRepository, RuleConverter ruleConverter){
        this.ruleRepository = ruleRepository;
        this.ruleConverter = ruleConverter;
    }


    @Override
    public RuleDTO addRule(RuleDTO ruleDTO) {
        RuleEntity ruleEntity = ruleConverter.toEntity(ruleDTO);
        ruleEntity.setCreatedAt(LocalDateTime.now());
        ruleEntity.setUpdatedAt(LocalDateTime.now());
        ruleEntity = ruleRepository.save(ruleEntity);
        return ruleConverter.toDTO(ruleEntity);
    }

    @Override
    public List<RuleDTO> allRules() {
        List<RuleEntity> ruleEntities = ruleRepository.findAll();
        return ruleConverter.toDTO(ruleEntities);
    }

    @Override
    public RuleDTO getRuleDetail(Long id) {
        return ruleRepository.findById(id)
                .map(ruleConverter::toDTO)
                .orElse(null);
    }

    @Override
    public RuleDTO updateRule(Long id, RuleDTO ruleDTO) {
        return ruleRepository.findById(id)
                .map(ruleEntity -> {
                    ruleEntity.setUpdatedAt(LocalDateTime.now());
                    return ruleConverter.toDTO(ruleEntity);
                }).orElse(ruleDTO);
    }

    @Override
    public void deleteRule(Long id) {
        ruleRepository.deleteById(id);
    }
}
