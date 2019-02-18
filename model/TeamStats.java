package csulb.cecs323.model;

import javax.persistence.*;


/**
 * Class for TeamStats of a team during a given split
 * Stores information on wins, losses, draws, number of games played, win/loss ratio
 */
@Entity
@Table(name = "teamstats")
public class TeamStats {


    private int wins;
    private int losses;
    private int draws;
    private int numOfGamesPlayed;
    private float winLossRatio;

    @EmbeddedId
    private TeamStatsID id;


    @ManyToOne
    @MapsId(("teamID"))
    private Team team;


    @ManyToOne
    @MapsId("splitCode")
    private Split split;


    public Long getTeamID() {
        return id.getTeamID();
    }

    public void setTeamID(Long teamID) {
        this.id.setTeamID(teamID);
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getNumOfGamesPlayed() {
        return numOfGamesPlayed;
    }

    public void setNumOfGamesPlayed(int numOfGamesPlayed) {
        this.numOfGamesPlayed = numOfGamesPlayed;
    }

    public float getWinLossRatio() {
        return winLossRatio;
    }

    public void setWinLossRatio(float winLossRatio) {
        this.winLossRatio = winLossRatio;}
}
