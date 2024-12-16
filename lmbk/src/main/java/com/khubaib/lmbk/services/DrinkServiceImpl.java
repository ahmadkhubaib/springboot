package com.khubaib.lmbk.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.khubaib.lmbk.model.Drink;
import com.khubaib.lmbk.model.DrinkStyle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DrinkServiceImpl implements DrinkService {

    private Map<UUID, Drink> dinkMap;

    public DrinkServiceImpl(){

        this.dinkMap = new HashMap<>();

        Drink drink1 = Drink.builder()
        .id(UUID.randomUUID())
        .drinkName("Turkish")
        .drinkStyle(DrinkStyle.KIZILAY)
        .version(1)
        .price(new BigDecimal("12.99"))
        .quantityOnHand(20)
        .upc("upcoming")
        .createdDate(LocalDateTime.now())
        .updatedDate(LocalDateTime.now())
        .build();

        Drink drink2 = Drink.builder()
        .id(UUID.randomUUID())
        .drinkName("German")
        .drinkStyle(DrinkStyle.ZELTER)
        .version(1)
        .price(new BigDecimal("15.99"))
        .quantityOnHand(10)
        .upc("downgoing")
        .createdDate(LocalDateTime.now())
        .updatedDate(LocalDateTime.now())
        .build();

        Drink drink3 = Drink.builder()
        .id(UUID.randomUUID())
        .drinkName("Amercian")
        .drinkStyle(DrinkStyle.GINGER_ALE)
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
    public Drink getDrinkById(UUID id) {
        return dinkMap.get(id);
    }

    @Override
    public List<Drink> listDrinks() {
        log.debug("in list drink");
        return new ArrayList<>(dinkMap.values());
    }
}