package csulb.cecs323.model;

import javax.persistence.*;

/**
 * Class for the composite primary key of PlayerMatchStats
 * Made up of playerID, matchID
 */
@Embeddable
public class PlayerMatchStatsID {

    private Long playerID;
    private Long matchID;

    public Long getPlayerID() {
        return playerID;
    }

    public void setPlayerID(Long playerID) {
        this.playerID = playerID;
    }

    public Long getMatchID() {
        return matchID;
    }

    public void setMatchID(Long matchID) {
        this.matchID = matchID;
    }
}
