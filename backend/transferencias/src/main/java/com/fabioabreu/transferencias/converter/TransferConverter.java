package com.fabioabreu.transferencias.converter;

import com.fabioabreu.transferencias.dto.TransferDTO;
import com.fabioabreu.transferencias.entity.TransferEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransferConverter {
    private final ModelMapper modelMapper;

    public TransferConverter(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public TransferDTO toDTO (TransferEntity transferEntity){
        return modelMapper.map(transferEntity, TransferDTO.class);
    }
    public TransferEntity toEntity (TransferDTO transferDTO){
        return modelMapper.map(transferDTO, TransferEntity.class);
    }

    public List<TransferDTO> toDTO (List<TransferEntity> transferEntities){
        return transferEntities.stream()
                .map(this::toDTO)
                .toList();
    }
}
