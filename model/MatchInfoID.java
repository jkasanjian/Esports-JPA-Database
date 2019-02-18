package csulb.cecs323.model;


import javax.persistence.*;

/**
 * Class for the composite primary key of MatchInfo
 * Made up of teamID and matchID
 */
@Embeddable
public class MatchInfoID {

    private Long teamID;
    private Long matchID;


    public Long getTeamID() {
        return teamID;
    }

    public void setTeamID(Long teamID) {
        this.teamID = teamID;
    }

    public Long getMatchID() {
        return matchID;
    }

    public void setMatchID(Long matchID) {
        this.matchID = matchID;
    }

}
