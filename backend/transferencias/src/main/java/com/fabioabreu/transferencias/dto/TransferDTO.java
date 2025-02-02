package com.fabioabreu.transferencias.dto;

import com.fabioabreu.transferencias.entity.RuleEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferDTO {
    private Long id;
    @NotNull
    private String originAccount;
    @NotNull
    private String destinyAccount;
    @NotNull
    private double transferValue;
    private double taxValue;
    @NotNull
    private Date transferDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
