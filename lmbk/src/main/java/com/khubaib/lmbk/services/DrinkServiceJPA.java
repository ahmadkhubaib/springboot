package com.khubaib.lmbk.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
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
    public Optional<DrinkDTO> getDrinkById(UUID id) {
        return
            Optional
            .ofNullable(
                drinkMapper
                .drinkToDrinkDto(
                    drinkRepository
                    .findById(id)
                    .orElse(null)
                )
            );
    
    }
    @Override
    public List<DrinkDTO> listDrinks() {
        return 
            drinkRepository
            .findAll()
            .stream()
            .map(drinkMapper::drinkToDrinkDto)
            .collect(Collectors.toList());
    }
    @Override
    public DrinkDTO saveNewDrink(DrinkDTO drink) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveNewDrink'");
    }
    @Override
    public void updateDrinkById(UUID drinkId, DrinkDTO drink) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateDrinkById'");
    }
    @Override
    public void deleteDrinkById(UUID drinkId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteDrinkById'");
    }

}
