package com.khubaib.lmbk.repositories;

import com.khubaib.lmbk.entities.Drink;
import com.khubaib.lmbk.entities.DrinkStyle;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DrinkRepositoryTest {

    @Autowired
    DrinkRepository drinkRepository;

    @Test
    void saveDrinkNameTooLongException() {
        assertThrows(ConstraintViolationException.class, () -> {
            drinkRepository.save(
                    Drink.builder()
                            .drinkName("qwertyuiopoiuytresafghjklkjhgcvbji8765432wertyuiuhgvbnkjhgtrewertyujhcvbjhgf")
                            .drinkStyle(DrinkStyle.ZELTER)
                            .price(BigDecimal.TEN)
                            .upc("123")
                            .build()
            );
            drinkRepository.flush();
        });
    }

    @Test
    void testSaveDrink() {
        Drink myDrink = drinkRepository.save(
                Drink.builder()
                        .drinkName("test")
                        .drinkStyle(DrinkStyle.ZELTER)
                        .price(BigDecimal.TEN)
                        .upc("123")
                        .build()
        );
        drinkRepository.flush();

        assertThat(myDrink.getDrinkName()).isEqualTo("test");
        assertThat(myDrink.getId()).isNotNull();
    }
}