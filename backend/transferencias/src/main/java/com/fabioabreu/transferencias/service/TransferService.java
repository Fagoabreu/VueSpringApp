package com.fabioabreu.transferencias.service;

import com.fabioabreu.transferencias.dto.TransferDTO;

import java.util.List;

public interface TransferService {
    TransferDTO addTransfer(TransferDTO transferDTO);

    List<TransferDTO> allTransfers();

    TransferDTO getTransferDetail(Long id);

    TransferDTO updateTransfer(Long id, TransferDTO transferDTO);

    void deleteTransfer(Long id);
}
