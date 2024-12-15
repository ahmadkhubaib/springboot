package com.khubaib.lmbk.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Drink {

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
