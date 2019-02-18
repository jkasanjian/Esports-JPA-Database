package csulb.cecs323.model;

import javax.persistence.*;

/**
 * Class for composite primary key of TeamStats
 * Made up of teamID, splitCode
 */
@Embeddable
public class TeamStatsID {

    @Column(name = "team_id")
    private Long teamID;
    @Column(name = "split_id")
    private String splitCode;


    public Long getTeamID() {
        return teamID;
    }

    public void setTeamID(Long teamID) {
        this.teamID = teamID;
    }

    public String getSplitID() {
        return splitCode;
    }

    public void setSplitID(String splitCode) {
        this.splitCode = splitCode;
    }
}
