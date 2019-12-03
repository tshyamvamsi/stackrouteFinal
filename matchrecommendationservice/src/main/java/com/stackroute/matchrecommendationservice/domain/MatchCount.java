package com.stackroute.matchrecommendationservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "match_recomendation")
public class MatchCount {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "uniqueId")
    private int uniqueId;

    @Column(name = "team1")
    private String teamOne;

    @Column(name = "team2")
    private String teamTwo;

    @Column(name = "date")
    private String matchDate;

    @Column(name = "match_started", columnDefinition = "TINYINT(1)")
    private boolean matchStarted;

    @Column(name = "user_id")
    private String userId;

    public MatchCount() {
    }

    public MatchCount(int id, int uniqueId, String teamOne, String teamTwo, String matchDate, boolean matchStarted, String userId) {
        this.id = id;
        this.uniqueId = uniqueId;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.matchDate = matchDate;
        this.matchStarted = matchStarted;
        this.userId = userId;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * @return the teamOne
     */
    public String getTeamOne() {
        return teamOne;
    }

    /**
     * @param teamOne
     *            the teamOne to set
     */
    public void setTeamOne(String teamOne) {
        this.teamOne = teamOne;
    }

    /**
     * @return the teamTwo
     */
    public String getTeamTwo() {
        return teamTwo;
    }

    /**
     * @param teamTwo
     *            the teamTwo to set
     */
    public void setTeamTwo(String teamTwo) {
        this.teamTwo = teamTwo;
    }



    /**
     * @return the uniqueId
     */
    public int getUniqueId() {
        return uniqueId;
    }

    /**
     * @param uniqueId the uniqueId to set
     */
    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * @return the matchDate
     */
    public String getMatchDate() {
        return matchDate;
    }

    /**
     * @param matchDate the matchDate to set
     */
    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    /**
     * @return the matchStarted
     */
    public boolean isMatchStarted() {
        return matchStarted;
    }

    /**
     * @param matchStarted
     *            the matchStarted to set
     */
    public void setMatchStarted(boolean matchStarted) {
        this.matchStarted = matchStarted;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }


}

