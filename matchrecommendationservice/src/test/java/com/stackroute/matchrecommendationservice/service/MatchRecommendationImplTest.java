package com.stackroute.matchrecommendationservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.matchrecommendationservice.domain.MatchCount;
import com.stackroute.matchrecommendationservice.exception.MatchAlreadyExistsException;
import com.stackroute.matchrecommendationservice.exception.MatchNotFoundException;
import com.stackroute.matchrecommendationservice.repository.MatchCountRepository;
import org.mockito.stubbing.OngoingStubbing;


public class MatchRecommendationImplTest {

    @Mock
    private transient MatchCountRepository matchCountRepo;

    private transient MatchCount matchCount;

    @InjectMocks
    private transient MatchRecommendationImpl matchRecommendationImpl;

    static List<MatchCount> matchCounts;

    static List<Map<String, Object>> matchRecommendations;

    transient Optional<MatchCount> options;
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
    public void testMockCreation() {
        assertNotNull("JpaRepository creation failed: use @InjectMocks on matchServiceImpl", matchCount);
    }

}

