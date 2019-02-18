package csulb.cecs323.model;


import javax.persistence.*;
import java.util.List;

/**
 * Class for TeamMembers on a team
 * Stores information on name, gamertag, age, gender, position, main champion, and their team
 */
@Entity
@Table(name = "teammembers")
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fName;
    private String lName;
    private String gamertag;
    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;   // M/F/N

    @Enumerated(EnumType.STRING)
    private Position position; // top/mid/jungle/support/adc

    private String mainChampion;


    @ManyToOne
    private Team team;

    @OneToMany(mappedBy = "player")
    private List<PlayerMatchStats> matchStats;



    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getGamertag() {
        return gamertag;
    }

    public void setGamertag(String gamertag) {
        this.gamertag = gamertag;
    }

    public int getAge() {
        return age;
    }

    public String getMainChampion() {
        return mainChampion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMainChampion(String mainChampion) {
        this.mainChampion = mainChampion;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<PlayerMatchStats> getMatchStats() {
        return matchStats;
    }

    public void setMatchStats(List<PlayerMatchStats> matchStats) {
        this.matchStats = matchStats;
    }
}
