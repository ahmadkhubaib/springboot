package com.khubaib.lmbk.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.khubaib.lmbk.dto.DrinkDTO;

public interface DrinkService {

    Optional<DrinkDTO> getDrinkById(UUID id);

    List<DrinkDTO> listDrinks();

    DrinkDTO saveNewDrink(DrinkDTO drink);

    Optional<DrinkDTO> updateDrinkById(UUID drinkId, DrinkDTO drink);

    Boolean deleteDrinkById(UUID drinkId);
}
