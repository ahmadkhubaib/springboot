package com.khubaib.lmbk.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.khubaib.lmbk.entities.DrinkStyle;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DrinkDTO {

    private UUID id;
    private String drinkName;
    private Integer version;
    private BigDecimal price;
    private DrinkStyle drinkStyle;
    private Integer quantityOnHand;
    private String upc;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
