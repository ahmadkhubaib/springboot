package com.khubaib.lmbk.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.springframework.stereotype.Service;

import com.khubaib.lmbk.entities.DrinkCSVRecord;
import com.opencsv.bean.CsvToBeanBuilder;

@Service
public class DrinkCSVServiceImpl implements DrinkCSVService{

    @Override
    public List<DrinkCSVRecord> convertCSV(File csvFile) {
        try {
            List<DrinkCSVRecord> records;
            records = new CsvToBeanBuilder<DrinkCSVRecord>(new FileReader(csvFile))
            .withType(DrinkCSVRecord.class)
            .build()
            .parse();
            return records;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
