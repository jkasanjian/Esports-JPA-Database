package csulb.cecs323.model;

import javax.persistence.*;

/**
 * Class for Player match stats
 * Stores info on a player's performance during a match
 */
@Entity
@Table(name = "playermatchstats")
public class PlayerMatchStats {

    private int kills;
    private int deaths;
    private int assists;
    private int gold;
    private int cs;
    private double kd;
    private String champion; //should be enumerated, but hundreds of options

    @EmbeddedId
    private PlayerMatchStatsID pStatsID;

    @ManyToOne
    @MapsId("playerID")
    private TeamMember player;

    @ManyToOne
    @MapsId("matchID")
    private Match match;


}
