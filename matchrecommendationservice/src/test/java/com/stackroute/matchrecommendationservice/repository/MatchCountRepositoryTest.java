package com.stackroute.matchrecommendationservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.stackroute.matchrecommendationservice.domain.MatchCount;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MatchCountRepositoryTest {

    @Autowired
    private MatchCountRepository matchCountRepository;

    private MatchCount matchCount;

    @Before
    public void setup() {
        matchCount = new MatchCount(1, 12345, "India", "Australia", "2019-08-08", false, "1");
    }
    @After
    public void tearDown() {
        matchCountRepository.deleteAll();
    }
    @Test
    public void testSaveMatchCount() throws Exception {
        matchCountRepository.save(matchCount);
        final MatchCount saveMatch = matchCountRepository.getOne(1);
        assertThat(saveMatch.getId()).isEqualTo(1);
    }

    @Test
    public void testUpdateMatchCount() throws Exception {
        MatchCount updateMatchCount = matchCountRepository.save(matchCount);
        final MatchCount updatedMatch = matchCountRepository.getOne(updateMatchCount.getId());
        updatedMatch.setMatchStarted(true);
        matchCountRepository.save(updatedMatch);
        final MatchCount updateDMatch = matchCountRepository.getOne(updatedMatch.getId());
        assertEquals(updateDMatch.isMatchStarted(), true);
    }

    @Test
    public void testDeleteMatchCount() throws Exception {
        MatchCount saveMatchCount = matchCountRepository.save(matchCount);
        final MatchCount deleteMatch = matchCountRepository.getOne(saveMatchCount.getId());
        assertThat(deleteMatch.getId()).isEqualTo(saveMatchCount.getId());
        matchCountRepository.delete(deleteMatch);
        //assertEquals(Optional.empty(), matchRepository.findById(saveMatch.getId()));
    }

    @Test
    public void testGetAllMatches() throws Exception {
        MatchCount match1 = matchCountRepository.save(matchCount);
        MatchCount match2 = matchCountRepository.save(matchCount);
        List<MatchCount> matchList = matchCountRepository.findAll();
        boolean flag=false;
        if(matchList.size()>0){
            flag=true;
        }
        assertEquals(true,flag);

    }



}
