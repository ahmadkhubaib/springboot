package com.khubaib.lmbk.services;

import java.util.UUID;

import com.khubaib.lmbk.model.Drink;

public interface DrinkService {

    Drink getDrinkById(UUID id);
}
