package com.khubaib.lmbk.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khubaib.lmbk.entities.Drink;

public interface DrinkRepository extends JpaRepository<Drink, UUID>{

    List<Drink> findAllByDrinkNameIgnoreCase(String drinkName);
}
