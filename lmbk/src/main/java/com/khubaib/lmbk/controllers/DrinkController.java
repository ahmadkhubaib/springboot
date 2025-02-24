package com.khubaib.lmbk.controllers;

import java.util.UUID;

import com.khubaib.lmbk.entities.DrinkStyle;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.khubaib.lmbk.dto.DrinkDTO;
import com.khubaib.lmbk.services.DrinkService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@RestController
public class DrinkController {

    private final DrinkService drinkService;

    public static final String DRINK_PATH = "/api/v1/drinks";
    public static final String DRINK_PATH_ID = DRINK_PATH + "/{drinkId}";

    @DeleteMapping(DRINK_PATH_ID)
    public ResponseEntity<HttpStatus.Series> deleteById(@PathVariable("drinkId") UUID drinkId) throws Exception {

        if(!drinkService.deleteDrinkById(drinkId)){
            throw new Exception("Could not delete drink");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(DRINK_PATH_ID)
    public ResponseEntity<HttpStatus.Series> updateById(@PathVariable("drinkId") UUID drinkId, @RequestBody DrinkDTO drink) throws Exception {

        if(drinkService.updateDrinkById(drinkId, drink).isEmpty()){
            throw new Exception("Drink not found");
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(DRINK_PATH)
    public ResponseEntity<HttpStatus.Series> handlePost(@Validated @RequestBody DrinkDTO drink) {

        DrinkDTO savedDrink = drinkService.saveNewDrink(drink);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location", DRINK_PATH + "/" + savedDrink.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @GetMapping(value = DRINK_PATH_ID)
    public DrinkDTO getDrinkById(@PathVariable("drinkId") UUID drinkId) {
        log.debug("In controller");
        return drinkService.getDrinkById(drinkId).orElseThrow();
    }

    @GetMapping(DRINK_PATH)
    public Page<DrinkDTO> listDrinks(
            @RequestParam(required = false) String drinkName,
            @RequestParam(required = false) DrinkStyle drinkStyle,
            @RequestParam(required = false) boolean showInventory,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNumber) {
        return drinkService.listDrinks(drinkName, drinkStyle, showInventory, pageSize, pageNumber);
    }
}
