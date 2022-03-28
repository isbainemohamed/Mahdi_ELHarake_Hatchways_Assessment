package com.example.hatchway_assessment_mahdielharake;

import com.example.hatchway_assessment_mahdielharake.Controller.Controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(Controller.class)
public class ControllerTest {
    @Autowired
    MockMvc mockMvc;
    // Test function to test the get posts by tag
    @Test
    public void getPByTag_success() throws Exception {
        //Mockito.when(patientRecordRepository.findById(RECORD_1.getPatientId())).thenReturn(java.util.Optional.of(RECORD_1));
    //get all posts with culture tag
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/posts/?tags=culture")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[*].tags[*]", equalToIgnoringCase("culture")));

    }

    // Test function to test the sortBy
    @Test
    public void getPByTagSortBy_success() throws Exception {
        //get all posts with culture tag and sort them by reads:
        // default direction is asc,
        // so we must have the first element with the lowest reads :312
        // And the last element should be 97868
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/posts/?tags=culture&sortBy=reads")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[0].reads", equalToIgnoringCase("312")))
                .andExpect(jsonPath("$[-1].reads", equalToIgnoringCase("97868")));

    }

    // Test function to test the diretion
    @Test
    public void getPByTagSortByDirection_success() throws Exception {
        //get all posts with culture tag and sort them by reads:
        // default direction is asc,
        // so we must have the first element with the lowest reads :312
        // And the last element should be 97868
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/posts/?tags=culture&sortBy=reads&direction=desc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[-1].reads", equalToIgnoringCase("312")))
                .andExpect(jsonPath("$[0].reads", equalToIgnoringCase("97868")));

    }
}
