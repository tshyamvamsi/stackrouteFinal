package com.stackroute.matchrecommendationservice.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stackroute.matchrecommendationservice.repository.MatchCountRepository;
import com.stackroute.matchrecommendationservice.service.MatchRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stackroute.matchrecommendationservice.domain.MatchCount;
import com.stackroute.matchrecommendationservice.exception.MatchAlreadyExistsException;
import com.stackroute.matchrecommendationservice.exception.MatchNotFoundException;
import com.stackroute.matchrecommendationservice.service.MatchRecommendationService;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/matchservice")
public class MatchRecommendationController {


    private ResponseEntity responseEntity;

    private MatchRecommendationService matchRecommendationService;


    @Autowired
    public MatchRecommendationController(final MatchRecommendationService matchService) {
        this.matchRecommendationService = matchService;
    }



    @PostMapping (path = "/{userId}/match")
    @ExceptionHandler(MatchAlreadyExistsException.class)
    public ResponseEntity<?> saveNewMatch(@RequestBody MatchCount match, @PathVariable("userId") String userId) throws MatchAlreadyExistsException {
        try {
            match.setUserId(userId);
            matchRecommendationService.saveMatchRecommendation(match);
            responseEntity = new ResponseEntity<MatchCount>(match, HttpStatus.CREATED);
        } catch (MatchAlreadyExistsException e) {
            responseEntity = new ResponseEntity<String>("{\"message\" : \"" + e.getMessage() + "\" }",
                    HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateMatch(@PathVariable final Integer id, @RequestBody MatchCount match) {

        try {
            matchRecommendationService.updateMatchRecommendation(match);
            responseEntity = new ResponseEntity<MatchCount>(match, HttpStatus.OK);
        } catch (MatchNotFoundException e) {
            responseEntity = new ResponseEntity<String>("{\"message\" : \"" + e.getMessage() + "\" }",
                    HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @DeleteMapping(path = "/{userId}/{id}")
    public ResponseEntity<?> deleteMatch(@PathVariable final Integer id, @PathVariable("userId") String userId) {

        try {
            matchRecommendationService.deleteMatchRecommendationById(id);
            responseEntity = new ResponseEntity<String>("Match deleted succesfully", HttpStatus.OK);
        } catch (MatchNotFoundException e) {
            responseEntity = new ResponseEntity<String>("{\"message\" : \"" + e.getMessage() + "\" }",
                    HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<?> fetchMatchById(@PathVariable final Integer id) {

        try {
            MatchCount match= matchRecommendationService.getMatchRecommendationById(id);
            responseEntity = new ResponseEntity<MatchCount>(match, HttpStatus.OK);
        } catch (MatchNotFoundException e) {
            responseEntity = new ResponseEntity<String>("{\"message\" : \"" + e.getMessage() + "\" }",
                    HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


    @GetMapping
    public ResponseEntity<?> fetchAllMatches() {

        return new ResponseEntity<List<MatchCount>>(matchRecommendationService.getAllMatchesRecommendation(), HttpStatus.OK);

    }


    @GetMapping(path ="/{userId}/matches")
    public ResponseEntity<?> fetchMyMatches(HttpServletRequest request, @PathVariable("userId") String userId) {
        return new ResponseEntity<List<MatchCount>>(matchRecommendationService.getMyMatchesRecommendation(userId), HttpStatus.OK);

    }


    @GetMapping("/{userId}/matchrecommendations")
    public List<Map<String, Object>> getMatchrecommendations(){

        List<Map<String, Object>> matchrecommendations = matchRecommendationService.getByMatchrecommendation();
        return matchrecommendations;
    }




}

