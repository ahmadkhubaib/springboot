package com.khubaib.lmbk.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.khubaib.lmbk.dto.DrinkDTO;
import com.khubaib.lmbk.mappers.DrinkMapper;
import com.khubaib.lmbk.services.DrinkService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/drinks")
public class DrinkController {

    private final DrinkService drinkService;

    @PutMapping("{drinkId}")
    public ResponseEntity<HttpStatus.Series> updateById(@PathVariable("drinkId") UUID drinkId, @RequestBody DrinkDTO drink) {

        drinkService.updateDrinkById(drinkId, drink);

        return new ResponseEntity<HttpStatus.Series>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<HttpStatus.Series> handlePost(@RequestBody DrinkDTO drink) {

        DrinkDTO savedDrink = drinkService.saveNewDrink(drink);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location", "/api/v1/drinks/"+savedDrink.getId());

        return new ResponseEntity<HttpStatus.Series>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.GET, value = "{drinkId}")
    public DrinkDTO getDrinkById(@PathVariable("drinkId") UUID drinkId) {
        log.debug("In controller");
        return drinkService.getDrinkById(drinkId).orElseThrow();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<DrinkDTO> listDrinks() {
        return drinkService.listDrinks();
    }
}
