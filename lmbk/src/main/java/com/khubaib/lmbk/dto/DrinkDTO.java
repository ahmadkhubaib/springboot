package com.khubaib.lmbk.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.khubaib.lmbk.entities.DrinkStyle;

import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DrinkDTO {

    private UUID id;

    @NotBlank
    @NotNull
    private String drinkName;

    @Version
    private Integer version;

    @NotNull
    private BigDecimal price;

    @NotBlank
    @NotNull
    private DrinkStyle drinkStyle;
    
    private Integer quantityOnHand;

    @NotBlank
    @NotNull
    private String upc;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
