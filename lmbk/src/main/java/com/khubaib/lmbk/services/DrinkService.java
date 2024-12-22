package com.khubaib.lmbk.services;

import java.util.List;
import java.util.UUID;

import com.khubaib.lmbk.dto.DrinkDTO;

public interface DrinkService {

    DrinkDTO getDrinkById(UUID id);

    List<DrinkDTO> listDrinks();

    DrinkDTO saveDrink(DrinkDTO drink);

    void updateById(UUID drinkId, DrinkDTO drink);
}
