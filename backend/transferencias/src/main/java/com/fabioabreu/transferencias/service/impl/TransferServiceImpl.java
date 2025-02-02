package com.fabioabreu.transferencias.service.impl;

import com.fabioabreu.transferencias.converter.TransferConverter;
import com.fabioabreu.transferencias.dto.TransferDTO;
import com.fabioabreu.transferencias.entity.TransferEntity;
import com.fabioabreu.transferencias.repository.TransferRepository;
import com.fabioabreu.transferencias.service.TransferService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;
    private final TransferConverter transferConverter;

    public TransferServiceImpl(TransferRepository transferRepository, TransferConverter transferConverter){
        this.transferRepository = transferRepository;
        this.transferConverter = transferConverter;
    }


    @Override
    public TransferDTO addTransfer(TransferDTO transferDTO) {
        TransferEntity transferEntity = transferConverter.toEntity(transferDTO);
        transferEntity.setCreatedAt(LocalDateTime.now());
        transferEntity.setUpdatedAt(LocalDateTime.now());
        transferEntity = transferRepository.save(transferEntity);
        return transferConverter.toDTO(transferEntity);
    }

    @Override
    public List<TransferDTO> allTransfers() {
        List<TransferEntity> transferEntities = transferRepository.findAll();
        return transferConverter.toDTO(transferEntities);
    }

    @Override
    public TransferDTO getTransferDetail(Long id) {
        return transferRepository.findById(id)
                .map(transferConverter::toDTO)
                .orElse(null);
    }

    @Override
    public TransferDTO updateTransfer(Long id, TransferDTO transferDTO) {
        return transferRepository.findById(id)
                .map(transferEntity -> {
                    transferEntity.setUpdatedAt(LocalDateTime.now());
                    return transferConverter.toDTO(transferEntity);
                }).orElse(transferDTO);
    }

    @Override
    public void deleteTransfer(Long id) {
        transferRepository.deleteById(id);
    }
}
