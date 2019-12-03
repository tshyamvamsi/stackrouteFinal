package com.stackroute.matchrecommendationservice.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.stackroute.matchrecommendationservice.domain.MatchCount;
import com.stackroute.matchrecommendationservice.exception.MatchAlreadyExistsException;
import com.stackroute.matchrecommendationservice.service.MatchRecommendationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.After;

@RunWith(SpringRunner.class)
@WebMvcTest(MatchRecommendationController.class)
public class MatchRecommendationControllerTest {

    @Autowired
    private transient MockMvc mockMvc;

    @MockBean
    private transient MatchRecommendationService matchRecommendationService;

    private transient MatchCount matchCount;

    static List<MatchCount> matchCounts;

    static List<Map<String, Object>> matchRecommendations;

    private String token;

    @Before
    public void setup() {
        token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbHRhcCIsImlhdCI6MTU1MzA4MDA1OH0.guFZsKg_vgvM6ikkVKdL5avt0v2t8-ZldHfpkSWx10k";
        matchCounts = new ArrayList<MatchCount>();
        matchCount = new MatchCount( 1, 12345, "India", "Australia", "2019-08-08", false, "1");
        matchCounts.add(matchCount);
        matchCount = new MatchCount( 2, 12346, "Australia", "India", "2019-09-08", false, "2");
    }

    @After
    public void tearDown(){
        matchCounts = null;
        matchCount = null;
    }

   @Test
    public void testSaveMatchRecommendationSuccess() throws MatchAlreadyExistsException, Exception {

        when(matchRecommendationService.saveMatchRecommendation(matchCount)).thenReturn(true);
        mockMvc.perform(post("/api/v1/matchservice/shyamvamsi/match").header("authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(matchCount)))
                .andExpect(status().isCreated());

        verify(matchRecommendationService, times(1)).saveMatchRecommendation(any(MatchCount.class));
        verifyNoMoreInteractions(matchRecommendationService);
    }

    @Test
    public void testMatchRecommendations() throws Exception {
        when(matchRecommendationService.getByMatchrecommendation()).thenReturn(matchRecommendations);
        mockMvc.perform(get("/api/v1/matchservice/shyamvamsi/matchrecommendations", 1).header("authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(matchCount)))
                .andExpect(status().isOk());

        verify(matchRecommendationService, times(1)).getByMatchrecommendation();
        verifyNoMoreInteractions(matchRecommendationService);
    }

    @Test
    public void testUpdateMatchRecommendationSuccess() throws Exception {
        matchCount.setMatchStarted(true);
        when(matchRecommendationService.updateMatchRecommendation(matchCount)).thenReturn(true);
        mockMvc.perform(put("/api/v1/matchservice/{id}", 1).header("authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(matchCount))).andExpect(status().isOk());
        verify(matchRecommendationService, times(1)).updateMatchRecommendation(any(MatchCount.class));
        verifyNoMoreInteractions(matchRecommendationService);
    }

    @Test
    public void testDeleteMatchRecommendationById() throws Exception {
        when(matchRecommendationService.deleteMatchRecommendationById(1)).thenReturn(true);
        mockMvc.perform(delete("/api/v1/matchservice/shyamvamsi/{id}", 1).header("authorization", "Bearer " + token)).andExpect(status().isOk());
        verify(matchRecommendationService, times(1)).deleteMatchRecommendationById(1);
        verifyNoMoreInteractions(matchRecommendationService);
    }

    @Test
    public void testFetchAllMatchRecommendation() throws MatchAlreadyExistsException, Exception {
        when(matchRecommendationService.getAllMatchesRecommendation()).thenReturn(matchCounts);
        mockMvc.perform(get("/api/v1/matchservice/", 1).header("authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(matchCount))).andExpect(status().isOk());
        verify(matchRecommendationService, times(1)).getAllMatchesRecommendation();
        verifyNoMoreInteractions(matchRecommendationService);
    }




    private String jsonToString(final Object obj) throws JsonProcessingException {

        final ObjectMapper objectMapper = new ObjectMapper();
        final String jsonString = objectMapper.writeValueAsString(obj);
        return jsonString;
    }

}
