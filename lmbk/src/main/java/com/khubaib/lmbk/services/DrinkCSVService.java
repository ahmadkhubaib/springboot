package com.khubaib.lmbk.services;

import java.io.File;
import java.util.List;

import com.khubaib.lmbk.entities.DrinkCSVRecord;

public interface DrinkCSVService {

    List<DrinkCSVRecord> convertCSV(File csvFile);
}
