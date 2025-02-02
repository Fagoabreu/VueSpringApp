package com.fabioabreu.transferencias.converter;

import com.fabioabreu.transferencias.dto.RuleDTO;
import com.fabioabreu.transferencias.entity.RuleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RuleConverter {
    private final ModelMapper modelMapper;

    public RuleConverter(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public RuleDTO toDTO (RuleEntity ruleEntity){
        return modelMapper.map(ruleEntity, RuleDTO.class);
    }
    public RuleEntity toEntity (RuleDTO ruleDTO){
        return modelMapper.map(ruleDTO, RuleEntity.class);
    }

    public List<RuleDTO> toDTO (List<RuleEntity> ruleEntities){
        return ruleEntities.stream()
                .map(this::toDTO)
                .toList();
    }
}
