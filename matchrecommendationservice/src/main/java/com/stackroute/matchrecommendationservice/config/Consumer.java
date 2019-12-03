package com.stackroute.matchrecommendationservice.config;

import com.stackroute.matchrecommendationservice.exception.MatchAlreadyExistsException;
import com.stackroute.matchrecommendationservice.service.MatchRecommendationImpl;
import com.stackroute.matchrecommendationservice.domain.MatchCount;
import com.stackroute.rabbitmq.domain.MatchCountDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {


    @Autowired
    private MatchRecommendationImpl matchRecommendationService;

    @RabbitListener(queues = "user_queue")
    public void getUserDtoFromRabbitMq(MatchCountDTO matchCountDTO) throws MatchAlreadyExistsException {

        MatchCount matchCount = new MatchCount();

        matchCount.setUniqueId(matchCountDTO.getUniqueId());
        matchCount.setTeamOne(matchCountDTO.getTeamOne());
        matchCount.setTeamTwo(matchCountDTO.getTeamTwo());
        matchCount.setMatchDate(matchCountDTO.getMatchDate());
        matchCount.setMatchStarted(matchCountDTO.isMatchStarted());
        matchCount.setUserId(matchCountDTO.getUserId());


        matchRecommendationService.saveMatchRecommendation(matchCount);
    }

}
