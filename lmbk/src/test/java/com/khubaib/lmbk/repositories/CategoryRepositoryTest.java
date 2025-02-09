package com.khubaib.lmbk.repositories;

import com.khubaib.lmbk.entities.Category;
import com.khubaib.lmbk.entities.Drink;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    DrinkRepository drinkRepository;

    Drink testDrink;

    @BeforeEach
    void setUp() {
        testDrink = drinkRepository.findAll().getFirst();
    }

    @Transactional
    @Test
    void testAddCategory(){
        Category category = Category
                .builder()
                .description("test description")
                .build();

        testDrink.addCategory(category);

        Drink savedDrink = drinkRepository.save(testDrink);

        System.out.println(savedDrink);
    }
}