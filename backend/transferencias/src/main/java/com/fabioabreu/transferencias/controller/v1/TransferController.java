package com.fabioabreu.transferencias.controller.v1;

import com.fabioabreu.transferencias.dto.TransferDTO;
import com.fabioabreu.transferencias.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService){
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<TransferDTO> addTransfer(@RequestBody TransferDTO transferDTO){
        transferDTO = transferService.addTransfer(transferDTO);
        return ResponseEntity.status(200).body(transferDTO);
    }

    @GetMapping
    public ResponseEntity<List<TransferDTO>> allTransfers(){
        List<TransferDTO> transferDTOS = transferService.allTransfers();
        return ResponseEntity.status(200).body(transferDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferDTO> getTransferDetail(@PathVariable Long id){
        TransferDTO transferDTO = transferService.getTransferDetail(id);
        return ResponseEntity.status(200).body(transferDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TransferDTO> updateTransfer(@PathVariable Long id, TransferDTO transferDTO){
        transferDTO = transferService.updateTransfer(id,transferDTO);
        return ResponseEntity.status(200).body(transferDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransferencia(@PathVariable Long id){
        transferService.deleteTransfer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
