package com.stackroute.matchrecommendationservice.service;

import com.stackroute.matchrecommendationservice.domain.MatchCount;
import com.stackroute.matchrecommendationservice.exception.MatchAlreadyExistsException;
import com.stackroute.matchrecommendationservice.exception.MatchNotFoundException;

import java.util.List;
import java.util.List;
import java.util.Map;

public interface MatchRecommendationService {

    boolean saveMatchRecommendation(MatchCount match) throws MatchAlreadyExistsException;

    boolean updateMatchRecommendation(MatchCount match) throws MatchNotFoundException;

    boolean deleteMatchRecommendationById(int id) throws MatchNotFoundException;

    MatchCount getMatchRecommendationById(int id) throws MatchNotFoundException;


    List<MatchCount> getAllMatchesRecommendation();

    List<MatchCount> getMyMatchesRecommendation(String userId);

    List<Map<String, Object>> getByMatchrecommendation();
}
