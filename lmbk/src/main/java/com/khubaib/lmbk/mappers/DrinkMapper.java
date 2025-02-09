package com.khubaib.lmbk.mappers;

import org.mapstruct.Mapper;

import com.khubaib.lmbk.dto.DrinkDTO;
import com.khubaib.lmbk.entities.Drink;

@Mapper
public interface DrinkMapper {

    Drink drinkDtoToDrink(DrinkDTO drinkDto);

    DrinkDTO drinkToDrinkDto(Drink drink);
}
