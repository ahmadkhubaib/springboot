package com.khubaib.lmbk.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.khubaib.lmbk.dto.DrinkDTO;
import com.khubaib.lmbk.dto.DrinkStyleDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DrinkServiceImpl implements DrinkService {

    private Map<UUID, DrinkDTO> dinkMap;

    public DrinkServiceImpl(){

        this.dinkMap = new HashMap<>();

        DrinkDTO drink1 = DrinkDTO.builder()
        .id(UUID.randomUUID())
        .drinkName("Turkish")
        .drinkStyle(DrinkStyleDTO.KIZILAY)
        .version(1)
        .price(new BigDecimal("12.99"))
        .quantityOnHand(20)
        .upc("upcoming")
        .createdDate(LocalDateTime.now())
        .updatedDate(LocalDateTime.now())
        .build();

        DrinkDTO drink2 = DrinkDTO.builder()
        .id(UUID.randomUUID())
        .drinkName("German")
        .drinkStyle(DrinkStyleDTO.ZELTER)
        .version(1)
        .price(new BigDecimal("15.99"))
        .quantityOnHand(10)
        .upc("downgoing")
        .createdDate(LocalDateTime.now())
        .updatedDate(LocalDateTime.now())
        .build();

        DrinkDTO drink3 = DrinkDTO.builder()
        .id(UUID.randomUUID())
        .drinkName("Amercian")
        .drinkStyle(DrinkStyleDTO.GINGER_ALE)
        .version(1)
        .price(new BigDecimal("20.99"))
        .quantityOnHand(30)
        .upc("upcoming")
        .createdDate(LocalDateTime.now())
        .updatedDate(LocalDateTime.now())
        .build();

        dinkMap.put(drink1.getId(), drink1);
        dinkMap.put(drink2.getId(), drink2);
        dinkMap.put(drink3.getId(), drink3);
    }

    @Override
    public DrinkDTO getDrinkById(UUID id) {
        return dinkMap.get(id);
    }

    @Override
    public List<DrinkDTO> listDrinks() {
        log.debug("in list drink");
        return new ArrayList<>(dinkMap.values());
    }

    @Override
    public DrinkDTO saveDrink(DrinkDTO drink) {
        DrinkDTO savedDrink = DrinkDTO.builder()
        .id(UUID.randomUUID())
        .drinkName(drink.getDrinkName())
        .drinkStyle(drink.getDrinkStyle())
        .version(drink.getVersion())
        .price(drink.getPrice())
        .quantityOnHand(drink.getQuantityOnHand())
        .upc(drink.getUpc())
        .createdDate(LocalDateTime.now())
        .updatedDate(LocalDateTime.now())
        .build();

        dinkMap.put(savedDrink.getId(), savedDrink);

        return savedDrink;
    }

    @Override
    public void updateById(UUID drinkId, DrinkDTO drink) {
        DrinkDTO existingDrink = dinkMap.get(drinkId);

        existingDrink.setDrinkName(drink.getDrinkName());
        existingDrink.setDrinkStyle(drink.getDrinkStyle());
        existingDrink.setVersion(drink.getVersion());
        existingDrink.setPrice(drink.getPrice());
        existingDrink.setQuantityOnHand(drink.getQuantityOnHand());
        existingDrink.setUpc(drink.getUpc());
        existingDrink.setCreatedDate(LocalDateTime.now());
        existingDrink.setUpdatedDate(LocalDateTime.now());

        dinkMap.put(existingDrink.getId(), existingDrink);

    }
}