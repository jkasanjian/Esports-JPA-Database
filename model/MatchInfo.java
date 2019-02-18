package csulb.cecs323.model;

import javax.persistence.*;

/**
 * Association class between the many-to-many of Match and Team
 * Also keeps track of score, side, and outcome
 */
@Entity
@Table(name = "matchinfos")
public class MatchInfo {

    @EmbeddedId
    private MatchInfoID infoID;

    @Enumerated(EnumType.STRING)
    private Side side;     // red/blue

    @Enumerated(EnumType.STRING)
    private Outcome outcome;  // win/loss/draw

    private int score;


    @ManyToOne
    @MapsId(("teamID"))
    private Team team;

    @ManyToOne
    @MapsId(("matchID"))
    private Match match;

    public MatchInfoID getInfoID() {
        return infoID;
    }

    public void setInfoID(MatchInfoID infoID) {
        this.infoID = infoID;
    }


    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
