package com.fabioabreu.transferencias.controller.v1;

import com.fabioabreu.transferencias.dto.RuleDTO;
import com.fabioabreu.transferencias.service.RuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/rules")
public class RuleController {

    private final RuleService ruleService;

    public RuleController(RuleService ruleService){
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<RuleDTO> addTRule(@RequestBody RuleDTO ruleDTO){
        ruleDTO = ruleService.addRule(ruleDTO);
        return ResponseEntity.status(200).body(ruleDTO);
    }

    @GetMapping
    public ResponseEntity<List<RuleDTO>> allRules(){
        List<RuleDTO> ruleDTOS = ruleService.allRules();
        return ResponseEntity.status(200).body(ruleDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RuleDTO> getRuleDetail(@PathVariable Long id){
        RuleDTO ruleDTO = ruleService.getRuleDetail(id);
        return ResponseEntity.status(200).body(ruleDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RuleDTO> updateRule(@PathVariable Long id, RuleDTO ruleDTO){
        ruleDTO = ruleService.updateRule(id,ruleDTO);
        return ResponseEntity.status(200).body(ruleDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRule(@PathVariable Long id){
        ruleService.deleteRule(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
