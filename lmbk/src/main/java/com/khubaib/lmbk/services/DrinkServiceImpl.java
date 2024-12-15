package com.khubaib.lmbk.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.khubaib.lmbk.model.Drink;
import com.khubaib.lmbk.model.DrinkStyle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DrinkServiceImpl implements DrinkService {

    @Override
    public Drink getDrinkById(UUID id) {
        log.debug("in Service");
        return
        Drink
        .builder()
        .id(id)
        .drinkName("Turkish")
        .drinkStyle(DrinkStyle.KIZILAY)
        .version(1)
        .price(new BigDecimal("12.99"))
        .quantityOnHand(20)
        .upc("upcoming")
        .createdDate(LocalDateTime.now())
        .updatedDate(LocalDateTime.now())
        .build();
    }
}