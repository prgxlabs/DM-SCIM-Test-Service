package com.prgx.deductionManagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SCIMError {
    private Long code;
    private String message;
}
