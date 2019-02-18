package csulb.cecs323.model;

import javax.persistence.*;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Class representation of Split
 * Stores information on year, season, start date, end date, prize money
 */
@Entity
@Table(name = "splits")
public class Split {

    @Id
    private String code;

    private int year;

    @Enumerated(EnumType.STRING)
    private Season season; //   fall/spring

    private double prizeMoney;
    @Temporal(TemporalType.DATE)
    private GregorianCalendar startDate;
    @Temporal(TemporalType.DATE)
    private GregorianCalendar endDate;


    @OneToMany(mappedBy = "split")
    private List<Match> matches;

    @OneToMany(mappedBy = "split")
    private List<TeamStats> teamstats;



    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public List<TeamStats> getTeamstats() {
        return teamstats;
    }

    public void setTeamstats(List<TeamStats> teamstats) {
        this.teamstats = teamstats;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrizeMoney() {
        return prizeMoney;
    }

    public void setPrizeMoney(double prizeMoney) {
        this.prizeMoney = prizeMoney;
    }

    public GregorianCalendar getStartDate() {
        return startDate;
    }

    public void setStartDate(GregorianCalendar startDate) {
        this.startDate = startDate;
    }

    public GregorianCalendar getEndDate() {
        return endDate;
    }

    public void setEndDate(GregorianCalendar endDate) {
        this.endDate = endDate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }
}
