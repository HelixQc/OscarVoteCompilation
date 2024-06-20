package oscar.awardService.model;

import jakarta.persistence.*;
import oscar.electionServices.model.Vote;
import java.util.List;

/**
 * The Nomination class represents a nomination for an award in a particular year.
 * It contains information such as the year, obtained shares, nominated work,
 * associated votes, and the award for which the nomination is made.
 */
@Entity
@Table(name="Nomination")
public class Nomination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="year")
    private int year;
    @Column(name="obtainedShares")
    private double obtainedShares;
    @Column(name="nominatedWork")
    private String nominatedWork;

    @OneToMany
    @JoinColumn(name ="nomination_id")
    private List<Vote> votes;

    @ManyToMany
    @JoinTable(
            name = "AwardNomination",
            joinColumns = @JoinColumn(name = "nomination_id"),
            inverseJoinColumns = @JoinColumn(name = "award_id")
    )
    private List<Award> awards;

    // Empty constructor
    public Nomination() {}

    /**
     * Constructor to initialize a Nomination object with specified parameters.
     * @param id The unique identifier for the nomination
     * @param year The year of the nomination
     * @param obtainedShares The number of shares obtained by the nomination
     * @param nominatedWork The work nominated for the award
     * @param votes A list of votes associated with this nomination
     * @param awards The award for which the nomination is made
     */
    public Nomination(int id ,int year, double obtainedShares, String nominatedWork, List<Vote> votes, List<Award> awards) {
        this.id = id;
        this.year = year;
        this.obtainedShares = obtainedShares;
        this.nominatedWork = nominatedWork;
        this.votes = votes;
        this.awards = awards;
    }

    //JPA Constructor
    public Nomination (int year, double obtainedShares, String nominatedWork, List<Vote> votes, List<Award> awards){
        this.year = year;
        this.obtainedShares = obtainedShares;
        this.nominatedWork = nominatedWork;
        this.votes = votes;
        this.awards = awards;
    }

    // Getters for all fields
    public int getYear() {
        return year;
    }
    public double getObtainedShares() {
        return obtainedShares;
    }
    public String getNominatedWork() {
        return nominatedWork;
    }
    public List<Vote> getVotes() {
        return votes;
    }
    public List<Award> getAwards() {
        return awards;
    }
    public int getId() {
        return id;
    }



    // Setters for all fields
    public void setYear(int year) {
        this.year = year;
    }
    public void setObtainedShares(double obtainedShares) {
        this.obtainedShares = obtainedShares;
    }
    public void setNominatedWork(String nominatedWork) {
        this.nominatedWork = nominatedWork;
    }
    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
    public void setAwards(List<Award> awards) {
        this.awards = awards;
    }
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Generates a string representation of the Nomination object.
     * @return A string containing the year, obtained shares, nominated work,
     *         associated votes, and award of the nomination.
     */
    @Override
    public String toString() {
        return String.format("Nomination year = %d, obtainedShares = %s, nominatedWork = '%s'",
                year, obtainedShares, nominatedWork);
    }
}


