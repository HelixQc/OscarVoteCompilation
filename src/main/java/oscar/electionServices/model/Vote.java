package oscar.electionServices.model;

import jakarta.persistence.*;
import org.hibernate.sql.ast.tree.from.MappedByTableGroup;

import java.util.Date;

/**
 *This class is used to create Vote and stock it
 *in the database or in the memory repository
 */
@Entity
@Table(name = "Vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "shares")
    private Double shares;
    @Column(name = "execution")
    private Date execution;
    @Column(name="elector_id")
    private Integer elector_id;
    @Column(name = "nomination_id")
    private Integer nomination_id ;

    // Constructor with parameters
    public Vote(Double shares, Date execution, Integer elector_id, Integer nomination_id) {
        this.shares = shares;
        this.execution = execution;
        this.elector_id = elector_id;
        this.nomination_id = nomination_id;
    }

    // Empty constructor
    public Vote() {
    }

    //***Getters***//
    public Integer getId(){return id;}
    public double getShares() {
        return shares;
    }
    public Date getExecution() {
        return execution;
    }
    public Integer getElector_id() {
        return elector_id;
    }
    public Integer getNomination_id() {
        return nomination_id;
    }

    //***Setters***//
    public void setId(Integer id){this.id = id;}
    public void setShares(Double shares) {
        this.shares = shares;
    }
    public void setExecution(Date execution) {
        this.execution = execution;
    }
    public void setElector_id(Integer elector_id) {
        this.elector_id = elector_id;
    }
    public void setNomination_id(Integer nomination_id) {
        this.nomination_id = nomination_id;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "shares=" + shares +
                ", execution=" + execution ;
    }
}


