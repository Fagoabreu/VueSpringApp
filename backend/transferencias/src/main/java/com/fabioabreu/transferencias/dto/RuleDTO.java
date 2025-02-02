package com.fabioabreu.transferencias.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RuleDTO {
    private Long id;
    @NotNull
    private int dayMin;
    @NotNull
    private int dayMax;
    private double rate;
    private float taxPct;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
