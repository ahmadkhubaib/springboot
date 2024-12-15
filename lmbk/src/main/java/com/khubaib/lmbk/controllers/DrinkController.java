package com.khubaib.lmbk.controllers;

import java.util.UUID;

import org.springframework.stereotype.Controller;

import com.khubaib.lmbk.model.Drink;
import com.khubaib.lmbk.services.DrinkService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@AllArgsConstructor
@Controller
public class DrinkController {

    private final DrinkService drinkService;

    public Drink getDrinkById(UUID id) {
        log.debug("In controller");
        return drinkService.getDrinkById(id);
    }
}
