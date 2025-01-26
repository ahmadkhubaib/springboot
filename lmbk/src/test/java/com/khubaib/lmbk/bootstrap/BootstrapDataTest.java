package com.khubaib.lmbk.bootstrap;

import com.khubaib.lmbk.repositories.CustomerRepository;
import com.khubaib.lmbk.repositories.DrinkRepository;
import com.khubaib.lmbk.services.DrinkCSVService;
import com.khubaib.lmbk.services.DrinkCSVServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(DrinkCSVServiceImpl.class)
class BootstrapDataTest {

    @Autowired
    DrinkCSVService drinkCSVService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DrinkRepository drinkRepository;

    BootstrapData bootstrapData;

    @BeforeEach
    void setUp() {
        bootstrapData = new BootstrapData(drinkCSVService, drinkRepository, customerRepository);
    }

    @Test
    void Testrun() throws Exception {
        bootstrapData.run();

        assertThat(drinkRepository.count()).isEqualTo(100);
        assertThat(customerRepository.count()).isEqualTo(3);
    }
}