package com.khubaib.lmbk.bootstrap;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import com.khubaib.lmbk.entities.Customer;
import com.khubaib.lmbk.entities.Drink;
import com.khubaib.lmbk.entities.DrinkCSVRecord;
import com.khubaib.lmbk.entities.DrinkStyle;
import com.khubaib.lmbk.repositories.CustomerRepository;
import com.khubaib.lmbk.repositories.DrinkRepository;
import com.khubaib.lmbk.services.DrinkCSVService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final DrinkCSVService drinkCSVService;
    private final DrinkRepository drinkRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        loadCsvData();
        loadCustomerData();
    }

    private void loadCsvData() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csvs/drinks.csv");

        List<DrinkCSVRecord> records = drinkCSVService.convertCSV(file);

        drinkRepository.deleteAll();

        records.forEach(record -> {
            DrinkStyle drinkstyle = switch(record.getStyle()){
                case "ULUDAG" -> DrinkStyle.ULUDAG;
                case "KIZILAY" -> DrinkStyle.KIZILAY;
                case "ZELTER" -> DrinkStyle.ZELTER;
                case "GINGER_ALE" -> DrinkStyle.GINGER_ALE;
                default -> DrinkStyle.ZELTER;
            };
            drinkRepository.save(
                Drink
                .builder()
                .drinkName(StringUtils.abbreviate(record.getDrink(), 50))
                .drinkStyle(drinkstyle)
                .upc(record.getRow().toString())
                .price(BigDecimal.TEN)
                .quantityOnHand(record.getCount())
                .build()
            );
        });
    }

        private void loadCustomerData() {

        if (customerRepository.count() == 0) {
            Customer customer1 = Customer.builder()
                    .customerName("Customer 1")
                    .version(1)
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();

            Customer customer2 = Customer.builder()
                    .customerName("Customer 2")
                    .version(1)
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();

            Customer customer3 = Customer.builder()
                    .customerName("Customer 3")
                    .version(1)
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();

            customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3));
        }

    }
}
