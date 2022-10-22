package com.utku.demo.controller;

import com.utku.demo.data.dto.PropertyDto;
import com.utku.demo.service.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.awt.*;

import static com.utku.demo.data.PropertyControllerMockData.validPropertyResponseModel;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author Utku APAYDIN
 * @created 22/10/2022 - 5:23 PM
 */
@WebMvcTest(PropertyController.class)
class PropertyControllerTest {

    @MockBean
    PropertyService propertyService;
    @Autowired
    MockMvc mockMvc;
    private static final String VALID_CODE = "code1";

    @BeforeEach
    void setUp() {
        Mockito.when(propertyService.getPropertyByCode(VALID_CODE)).thenReturn(validPropertyResponseModel);
        Mockito.when(propertyService.getPropertyByCode(anyString())).thenReturn(new PropertyDto());
    }

    @Test
    void getPropertyByCode() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/property/code1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.code",is(VALID_CODE)));
    }

    @Test
    void getPropertyByCodeWithWrongCode() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/property/code2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.code",nullValue()));
    }

    @Test
    void getPropertyByCodeWithoutCode() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/property/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

}
