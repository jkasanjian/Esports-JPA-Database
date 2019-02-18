package csulb.cecs323.model;

import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.*;

/**
 * Match class to model information about matches
 * Attributes: date, lengthOfmatch (in seconds)
 */
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private GregorianCalendar date;

    private int lengthOfmatch; // in seconds

    @ManyToOne
    private Split split;

    @OneToMany(mappedBy = "match")
    private List<PlayerMatchStats> playerMatchStats;

    @OneToMany(mappedBy = "match")
    private List<MatchInfo> teamInfos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public int getLengthOfmatch() {
        return lengthOfmatch;
    }

    public void setLengthOfmatch(int lengthOfmatch) {
        this.lengthOfmatch = lengthOfmatch;
    }

    public Split getSplit() {
        return split;
    }

    public void setSplit(Split split) {
        this.split = split;
    }

    public List<PlayerMatchStats> getPlayerMatchStats() {
        return playerMatchStats;
    }

    public void setPlayerMatchStats(List<PlayerMatchStats> playerMatchStats) {
        this.playerMatchStats = playerMatchStats;
    }

    public List<MatchInfo> getTeamInfos() {
        return teamInfos;
    }

    public void setTeamInfos(List<MatchInfo> teamInfos) {
        this.teamInfos = teamInfos;
    }
}
