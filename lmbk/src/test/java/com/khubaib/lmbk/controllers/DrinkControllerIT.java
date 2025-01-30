package com.khubaib.lmbk.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khubaib.lmbk.entities.DrinkStyle;
import com.khubaib.lmbk.mappers.DrinkMapper;
import com.khubaib.lmbk.repositories.DrinkRepository;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class DrinkControllerIT {

    @Autowired
    DrinkController drinkController;

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    DrinkMapper drinkMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void testDrinkByStyle() throws Exception {
        mockMvc.perform(get(DrinkController.DRINK_PATH)
                        .queryParam("drinkStyle", DrinkStyle.KIZILAY.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(11)));
    }

    @Test
    void testDrinkByName() throws Exception {
        mockMvc.perform(get(DrinkController.DRINK_PATH)
                        .queryParam("drinkName", "KolA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(11)));
    }

    @Test
    void testDrinkByNameAndStyle() throws Exception {
        mockMvc.perform(get(DrinkController.DRINK_PATH)
                        .queryParam("drinkStyle", DrinkStyle.GINGER_ALE.name())
                        .queryParam("drinkName", "KolA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(11)));
    }

    @Test
    void testDrinkByNameAndStyleAndInventory() throws Exception {
        mockMvc.perform(get(DrinkController.DRINK_PATH)
                        .queryParam("drinkStyle", DrinkStyle.GINGER_ALE.name())
                        .queryParam("drinkName", "KolA")
                        .queryParam("showInventory", "true")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(11)))
                .andExpect(jsonPath("$.[0].quantityOnHand").value(IsNull.notNullValue()));
    }

    @Test
    void testPage2() throws Exception {
        mockMvc.perform(get(DrinkController.DRINK_PATH)
                .queryParam("pageSize", "30")
                .queryParam("pageNumber", "2")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()",is(30)));
    }
}
