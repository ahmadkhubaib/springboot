package com.khubaib.lmbk.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import com.khubaib.lmbk.entities.Drink;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.khubaib.lmbk.dto.DrinkDTO;
import com.khubaib.lmbk.mappers.DrinkMapper;
import com.khubaib.lmbk.repositories.DrinkRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

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
    public List<DrinkDTO> listDrinks(String drinkName) {

        List<Drink> drinkList;

        if(StringUtils.hasText(drinkName)){
            drinkList = listDrinkByName(drinkName);
        } else{
            drinkList = drinkRepository.findAll();
        }
        return
            drinkList
            .stream()
            .map(drinkMapper::drinkToDrinkDto)
            .collect(Collectors.toList());
    }

    public List<Drink> listDrinkByName(String drinkName) {
        return drinkRepository.findAllByDrinkNameIgnoreCase("%" + drinkName + "%");
    }

    @Override
    public DrinkDTO saveNewDrink(DrinkDTO drink) {
        return
            drinkMapper
            .drinkToDrinkDto(
                drinkRepository
                .save(
                    drinkMapper
                    .drinkDtoToDrink(drink)
                )
            );
    }
    @Override
    public Optional<DrinkDTO> updateDrinkById(UUID drinkId, DrinkDTO drink) {
       AtomicReference<Optional<DrinkDTO>> atomicReference = new AtomicReference<>();

       drinkRepository
       .findById(drinkId)
       .ifPresentOrElse(foundDrink -> {
            foundDrink.setDrinkName(drink.getDrinkName());
            foundDrink.setDrinkStyle(drink.getDrinkStyle());
            foundDrink.setPrice(drink.getPrice());
            atomicReference.set(
                Optional.of(
                    drinkMapper.drinkToDrinkDto(
                        drinkRepository.save(foundDrink)
                    )
                )
            );
            drinkRepository.save(foundDrink);
       }, () -> atomicReference.set(Optional.empty()));
       return atomicReference.get();
    }
    @Override
    public Boolean deleteDrinkById(UUID drinkId) {
       
       if(drinkRepository.existsById(drinkId)){
        drinkRepository.deleteById(drinkId);
        return true;
       }
       return false;
    }

}
