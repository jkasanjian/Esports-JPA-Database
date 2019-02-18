package csulb.cecs323.model;

import javax.persistence.*;

import java.util.*;

/**
 * Class for teams in the tournament
 * Stores team name, numberOfTrophies, members of team
 */
@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long teamID;

    private String name;
    private int numOfTrophies;

    @OneToMany(mappedBy = "team")
    private List<TeamMember> players;


    @OneToMany (mappedBy = "team", cascade = CascadeType.REMOVE)
    private List<TeamStats> teamStats;

    @OneToMany (mappedBy = "team")
    private List<MatchInfo> matches;


    public List<TeamMember> getPlayers() {
        return players;
    }

    public void setPlayers(List<TeamMember> players) {
        this.players = players;
    }

    public List<TeamStats> getTeamStats() {
        return teamStats;
    }

    public void setTeamStats(List<TeamStats> teamStats) {
        this.teamStats = teamStats;
    }

    public Long getTeamID() {
        return teamID;
    }

    public void setTeamID(Long teamID) {
        this.teamID = teamID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfTrophies() {
        return numOfTrophies;
    }

    public void setNumOfTrophies(int numOfTrophies) {
        this.numOfTrophies = numOfTrophies;
    }


    public void addTeamMember(TeamMember player){
        if(player.getTeam() == null)
            player.setTeam(this);
        if(player.getTeam().equals(this)){
            if(this.players == null)
                this.players = new ArrayList<>();
            if(! this.players.contains(player))
                this.players.add(player);
        }
    }
}
