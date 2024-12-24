package com.khubaib.lmbk.services;

import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.khubaib.lmbk.dto.DrinkDTO;
import com.khubaib.lmbk.mappers.DrinkMapper;
import com.khubaib.lmbk.repositories.DrinkRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Primary
public class DrinkServiceJPA implements DrinkService {

    private final DrinkRepository drinkRepository;
    private final DrinkMapper drinkMapper;


    @Override
    public DrinkDTO getDrinkById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDrinkById'");
    }

    @Override
    public List<DrinkDTO> listDrinks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listDrinks'");
    }

    @Override
    public DrinkDTO saveDrink(DrinkDTO drink) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveDrink'");
    }

    @Override
    public void updateById(UUID drinkId, DrinkDTO drink) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateById'");
    }

}
