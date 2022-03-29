package com.example.hatchway_assessment_mahdielharake;

import com.example.hatchway_assessment_mahdielharake.Controller.Controller;
import com.example.hatchway_assessment_mahdielharake.Model.Post;
import com.example.hatchway_assessment_mahdielharake.Service.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc
class HatchwayAssessmentMahdiElHarakeApplicationTests {
    @Autowired
    private Controller controller;


    @Test
    void contextLoads() {
        // check if the context is creating our controller
        assertThat(controller).isNotNull();
    }

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private PostService service;
    // Test function to test the get posts by tag

    @Test
    public void getByTag_success() throws Exception {
        //Mockito.when(patientRecordRepository.findById(RECORD_1.getPatientId())).thenReturn(java.util.Optional.of(RECORD_1));
        //get all posts with culture tag
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/posts/?tags=culture")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect((ResultMatcher) jsonPath("$", notNullValue()))
                .andReturn();


    }
        // Test function to test the sortBy
        @Test
        public void getByTagSortBy_success () throws Exception {
            //get all posts with culture tag and sort them by reads:

            mockMvc.perform(MockMvcRequestBuilders
                            .get("/api/posts/?tags=culture&sortBy=reads")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andExpect((ResultMatcher) jsonPath("$", notNullValue()));

        }

        // Test function to test the diretion
        @Test
        public void getPByTagSortByDirection_success () throws Exception {
            //get all posts with culture tag and sort them by reads:
            // default direction is asc,

            mockMvc.perform(MockMvcRequestBuilders
                            .get("/api/posts/?tags=culture&sortBy=reads&direction=desc")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andExpect((ResultMatcher) jsonPath("$", notNullValue()));

        }







}
