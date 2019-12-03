package com.stackroute.favouriteservice.controller;

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
import com.stackroute.favouriteservice.domain.Match;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.service.MatchService;
import com.stackroute.favouriteservice.controller.MatchController;
import org.junit.After;




@RunWith(SpringRunner.class)
@WebMvcTest(MatchController.class)
public class MatchControllerTest {



	@Autowired
	private transient MockMvc mockMvc;
	
	@MockBean
	private transient MatchService matchService;
	
	
	private transient Match match;
	
	static List<Match> matches;
	
	private String token;
	

	@Before
	public void setup() {
		token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbHRhcCIsImlhdCI6MTU1MzA4MDA1OH0.guFZsKg_vgvM6ikkVKdL5avt0v2t8-ZldHfpkSWx10k";
		matches = new ArrayList<Match>();
		match = new Match( 12345, "India", "Australia", "2019-08-08", false, "shyamvamsi");
		matches.add(match);
		match = new Match( 12346, "Pakistan", "America", "2019-10-08", true, "vamsishyam");
	}

	@After
	public void tearDown(){
		matches = null;
		match = null;
	}
	@Test
	public void testSaveMatchSuccess() throws MatchAlreadyExistsException, Exception {

		when(matchService.saveMatch(match)).thenReturn(true);
		mockMvc.perform(post("/api/v1/matchservice/shyamvamsi/match").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(match)))
				.andExpect(status().isCreated());


		verify(matchService, times(1)).saveMatch(any(Match.class));
		verifyNoMoreInteractions(matchService);
	}
	

	@Test
	public void testUpdateMatchSuccess() throws Exception {
		match.setMatchStarted(true);
		when(matchService.updateMatch(match)).thenReturn(true);
		mockMvc.perform(put("/api/v1/matchservice/{id}", 1).header("authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(match))).andExpect(status().isOk());
		verify(matchService, times(1)).updateMatch(any(Match.class));
		verifyNoMoreInteractions(matchService);
	}

	@Test
	public void testDeleteMatchById() throws Exception {
		when(matchService.deleteMatchById(1)).thenReturn(true);
		mockMvc.perform(delete("/api/v1/matchservice/shyamvamsi/{id}", 1).header("authorization", "Bearer " + token)).andExpect(status().isOk());
		verify(matchService, times(1)).deleteMatchById(1);
		verifyNoMoreInteractions(matchService);
	}
	

	@Test
	public void testFetchAllMatch() throws MatchAlreadyExistsException, Exception {
		when(matchService.getAllMatches()).thenReturn(matches);
		mockMvc.perform(get("/api/v1/matchservice/", 1).header("authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(match))).andExpect(status().isOk());
		verify(matchService, times(1)).getAllMatches();
		verifyNoMoreInteractions(matchService);
	}
	

	private String jsonToString(final Object obj) throws JsonProcessingException {
		
		final ObjectMapper objectMapper = new ObjectMapper();
		final String jsonString = objectMapper.writeValueAsString(obj);
		return jsonString;
	}

}
