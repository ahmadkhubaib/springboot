package com.khubaib.lmbk.repositories;

import com.khubaib.lmbk.entities.Customer;
import com.khubaib.lmbk.entities.Drink;
import com.khubaib.lmbk.entities.DrinkOrder;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DrinkOrderRepositoryTest {

    @Autowired
    DrinkOrderRepository drinkOrderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DrinkRepository drinkRepository;

    Drink testDrink;
    Customer testCustomer;

    @BeforeEach
    void setUp() {
        testDrink = drinkRepository.findAll().getFirst();
        testCustomer = customerRepository.findAll().getFirst();
    }

    @Transactional
    @Test
    void testAddDrinkOrder() {
        DrinkOrder drinkOrder = DrinkOrder
                .builder()
                .customerRef("from test")
                .customer(testCustomer)
                .build();

        DrinkOrder savedDrinkOrder = drinkOrderRepository.save(drinkOrder);

        System.out.println(savedDrinkOrder);

    }

}