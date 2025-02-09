package com.khubaib.lmbk.services;

import com.khubaib.lmbk.entities.DrinkCSVRecord;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class DrinkCSVServiceTest {

    DrinkCSVService drinkCSVService = new DrinkCSVServiceImpl();

    @Test
    void convertCSV() throws FileNotFoundException {

        File file = ResourceUtils.getFile("classpath:csvs/drinks.csv");

        List<DrinkCSVRecord> records = drinkCSVService.convertCSV(file);

        System.out.println(records.size());

        assertThat(records.size()).isEqualTo(100);
    }
}