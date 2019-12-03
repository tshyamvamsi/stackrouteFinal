package com.stackroute.rabbitmq.domain;

public class MatchCountDTO {

    private int uniqueId;
    private String teamOne;
    private String teamTwo;
    private String matchDate;
    private boolean matchStarted;
    private String userId;

    public MatchCountDTO() {
    }

    public MatchCountDTO(int uniqueId, String teamOne, String teamTwo, String matchDate, boolean matchStarted, String userId) {
        this.uniqueId = uniqueId;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.matchDate = matchDate;
        this.matchStarted = matchStarted;
        this.userId = userId;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(String teamOne) {
        this.teamOne = teamOne;
    }

    public String getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(String teamTwo) {
        this.teamTwo = teamTwo;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public boolean isMatchStarted() {
        return matchStarted;
    }

    public void setMatchStarted(boolean matchStarted) {
        this.matchStarted = matchStarted;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}

