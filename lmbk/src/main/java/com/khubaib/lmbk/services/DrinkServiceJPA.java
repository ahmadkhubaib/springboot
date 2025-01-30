package com.khubaib.lmbk.services;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import com.khubaib.lmbk.entities.Drink;
import com.khubaib.lmbk.entities.DrinkStyle;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 25;

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
    public Page<DrinkDTO> listDrinks(String drinkName, DrinkStyle drinkStyle, boolean showInventory, Integer pageSize, Integer pageNumber) {

        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);

        Page<Drink> drinkPage;

        if((StringUtils.hasText(drinkName)) && (drinkStyle == null)) {
            drinkPage = listDrinkByName(drinkName, pageRequest);
            System.out.println(drinkPage);
        } else if(!(StringUtils.hasText(drinkName)) && (drinkStyle != null)){
            drinkPage = listDrinkByDrinkStyle(drinkStyle, pageRequest);
        } else if((StringUtils.hasText(drinkName)) && (drinkStyle != null)){
            drinkPage = listDrinkByDrinkNameAndDrinkStyle(drinkName, drinkStyle, pageRequest);
        } else {
            drinkPage = drinkRepository.findAll(pageRequest);
        }

        if(!showInventory){
           drinkPage.forEach(drink -> drink.setQuantityOnHand(null));
        }

        return drinkPage.map(drinkMapper::drinkToDrinkDto);
    }

    public PageRequest buildPageRequest(Integer pageNumber, Integer pageSize) {
        int queryPageNumber;
        int queryPageSize;

        if(pageNumber != null && pageNumber > 0) {
            queryPageNumber = pageNumber - 1;
        } else {
            queryPageNumber = DEFAULT_PAGE;
        }

        if(pageSize == null) {
            queryPageSize = DEFAULT_PAGE_SIZE;
        } else {
            queryPageSize = pageSize;
        }

        Sort sort = Sort.by(Sort.Order.asc("drinkName"));

        return PageRequest.of(queryPageNumber, queryPageSize, sort);
    }

    public Page<Drink> listDrinkByDrinkStyle(DrinkStyle drinkStyle, Pageable pageable) {
        return drinkRepository.findAllByDrinkStyle(drinkStyle, pageable);
    }
    public Page<Drink> listDrinkByDrinkNameAndDrinkStyle(String drinkName, DrinkStyle drinkStyle, Pageable pageable) {
        return drinkRepository.findAllByDrinkNameAndDrinkStyle(drinkName, drinkStyle, pageable);
    }

    public Page<Drink> listDrinkByName(String drinkName, Pageable pageable) {
        return drinkRepository.findAllByDrinkNameIgnoreCase(drinkName, pageable);
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
