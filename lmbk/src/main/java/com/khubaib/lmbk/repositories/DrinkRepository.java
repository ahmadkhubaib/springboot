package com.khubaib.lmbk.repositories;

import java.util.UUID;

import com.khubaib.lmbk.entities.DrinkStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.khubaib.lmbk.entities.Drink;

public interface DrinkRepository extends JpaRepository<Drink, UUID>{

    Page<Drink> findAllByDrinkNameIgnoreCase(String drinkName, Pageable pageable);
    Page<Drink> findAllByDrinkStyle(DrinkStyle drinkStyle, Pageable pageable);
    Page<Drink> findAllByDrinkNameAndDrinkStyle(String drinkName, DrinkStyle drinkStyle, Pageable pageable);
}
