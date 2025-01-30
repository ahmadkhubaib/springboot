package com.khubaib.lmbk.services;

import java.util.Optional;
import java.util.UUID;

import com.khubaib.lmbk.dto.DrinkDTO;
import com.khubaib.lmbk.entities.DrinkStyle;
import org.springframework.data.domain.Page;

public interface DrinkService {

    Optional<DrinkDTO> getDrinkById(UUID id);

    Page<DrinkDTO> listDrinks(String drinkName, DrinkStyle drinkStyle, boolean showInventory, Integer pageSize, Integer pageNumber);

    DrinkDTO saveNewDrink(DrinkDTO drink);

    Optional<DrinkDTO> updateDrinkById(UUID drinkId, DrinkDTO drink);

    Boolean deleteDrinkById(UUID drinkId);
}
