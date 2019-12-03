package com.stackroute.favouriteservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.favouriteservice.domain.Match;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import com.stackroute.favouriteservice.repository.MatchRepository;


public class MatchServiceImplTest {
	@Mock
	private transient MatchRepository matchRepo;

	private transient Match match;

	@InjectMocks
	private transient MatchServiceImpl matchServiceImpl;

	transient Optional<Match> options;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		match = new Match( 12345, "India", "Australia", "2019-08-08", false, "shyamvamsi");
		options = Optional.of(match);
	}

	@After
	public void tearDown(){
		match = null;
	}

	@Test
	public void testMockCreation() {
		assertNotNull("JpaRepository creation failed: use @InjectMocks on matchServiceImpl", match);
	}

	@Test
	public void testSavematchSuccess() throws MatchAlreadyExistsException {
		when(matchRepo.findById(match.getId())).thenReturn(Optional.empty());
		when(matchRepo.save(match)).thenReturn(match);
	}

	@Test
	public void testDeleteMatchById() throws MatchNotFoundException {
		when(matchRepo.findById(1)).thenReturn(options);
		doNothing().when(matchRepo).delete(match);
		final boolean flag = matchServiceImpl.deleteMatchById(1);
		assertTrue("deleting movie field",flag);
		verify(matchRepo, times(1)).delete(match);
	}


	@Test
	public void testGetAllMatches() throws MatchNotFoundException{
		final ArrayList<Match> matchList = new ArrayList<Match>(1);
		when(matchRepo.findAll()).thenReturn(matchList);

		final List<Match> matchList1 = matchServiceImpl.getAllMatches();
		assertEquals("fetching all matches failed", matchList, matchList1);
		verify(matchRepo, times(1)).findAll();
	}


}
