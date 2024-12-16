package com.khubaib.lmbk.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.khubaib.lmbk.model.Drink;
import com.khubaib.lmbk.services.DrinkService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/drinks")
public class DrinkController {

    private final DrinkService drinkService;

    @RequestMapping(method = RequestMethod.GET, value = "{drinkId}")
    public Drink getDrinkById(@PathVariable("drinkId") UUID drinkId) {
        log.debug("In controller");
        return drinkService.getDrinkById(drinkId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Drink> listDrinks() {
        return drinkService.listDrinks();
    }
}
