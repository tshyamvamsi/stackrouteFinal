package com.stackroute.favouriteservice.service;

import java.util.List;
import java.util.Optional;

import com.stackroute.favouriteservice.config.Producer;
import com.stackroute.rabbitmq.domain.MatchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.domain.Match;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import com.stackroute.favouriteservice.repository.MatchRepository;


@Service
public class MatchServiceImpl implements MatchService{

	private Producer producer;
	private MatchRepository matchRepository;
	
	@Autowired
	public MatchServiceImpl(MatchRepository matchRepository, Producer producer) {
		this.matchRepository = matchRepository;
		this.producer = producer;
	}

	@Override
	public boolean saveMatch(Match match) throws MatchAlreadyExistsException {
		List<Match> fetchMatches = matchRepository.findAll();

		if(fetchMatches != null) {

			for (Match matchObj : fetchMatches) {
				if (matchObj.getId()== match.getId()) {
					throw new MatchAlreadyExistsException();
				}
			}
		}

		MatchDTO matchDto = new MatchDTO();
		matchDto.setUnique_id(match.getUnique_id());
		matchDto.setTeamOne(match.getTeamOne());
		matchDto.setTeamTwo(match.getTeamTwo());
		matchDto.setMatchDate(match.getMatchDate());
		matchDto.setMatchStarted(match.isMatchStarted());
		matchDto.setUserId(match.getUserId());

		/*final Optional<Match> matchObj = matchRepository.findById(match.getId());
		if (matchObj.isPresent()) {
			throw new MatchAlreadyExistsException();
		}*/
		matchRepository.save(match);
		producer.sendMessageToRabbitMq(matchDto);
		return true;
	}

	@Override
	public boolean updateMatch(Match updateMatch) throws MatchNotFoundException {
		final Optional<Match> matchOpt = matchRepository.findById(updateMatch.getId());
		
		if (matchOpt.isPresent()) {
			Match match = matchOpt.get();
			match.setMatchStarted(updateMatch.isMatchStarted());
			matchRepository.save(match);
			return true;
		}
		else {
			throw new MatchNotFoundException("Could not update match. Match not found");
		}
	}

	@Override
	public boolean deleteMatchById(int id) throws MatchNotFoundException {
		final Optional<Match> matchOpt = matchRepository.findById(id);
		
		if (matchOpt.isPresent()) {
			Match match = matchOpt.get();
			matchRepository.delete(match);
			return true;
		}
		else {
			throw new MatchNotFoundException("Could not delete match. Match not found");
		}
	}

	@Override
	public Match getMatchById(int id) throws MatchNotFoundException {
		final Optional<Match> matchOpt = matchRepository.findById(id);
		if (matchOpt.isPresent()) {
			return matchOpt.get();
		}
		else {
			throw new MatchNotFoundException("Match not found");
		}
	}

	@Override
	public List<Match> getAllMatches() {
		return matchRepository.findAll();
	}

	@Override
	public List<Match> getMyMatches(String userId) {
		return matchRepository.findByUserId(userId);
	}
}
