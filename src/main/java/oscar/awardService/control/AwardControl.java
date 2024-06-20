package oscar.awardService.control;


import oscar.awardService.model.Award;
import oscar.awardService.model.Nomination;
import oscar.awardService.persistence.JDBC.AwardDAO_DB_JDBC;
import oscar.awardService.persistence.JPA.AwardDAO_JPA;
import oscar.awardService.persistence.JPA.NominationDAO_JPA;
import oscar.awardService.persistence.Memory.AwardDAO_Memory;
import oscar.awardService.persistence.Memory.NominationDAO_Memory;
import oscar.awardService.persistence.JDBC.NominationDAO_DB_JDBC;
import oscar.electionServices.persistence.JDBC.VoteDAO_JDBC;
import oscar.electionServices.persistence.JPA.VoteDAO_JPA;
import oscar.electionServices.persistence.Memory.VoteDAO_Memory;

import java.util.List;
import java.util.Scanner;

/**
 * Implement UserStory
 * As a studio executive,
 * I want to nominate a
 * film for an awards category,
 * so that it can be considered
 * for an Oscar.
 */
public class AwardControl {

    Scanner sc = new Scanner(System.in);
    private int today = 2024;
    private int maxID = 0;

    //Memory
    private AwardDAO_Memory awardDAOMemory = new AwardDAO_Memory();
    private NominationDAO_Memory nominationDAOMemory = new NominationDAO_Memory();
    private VoteDAO_Memory voteDAOMemory = new VoteDAO_Memory();

    //JDBC
    private NominationDAO_DB_JDBC nominationDAO_DB_JDBC ;
    private AwardDAO_DB_JDBC awardDAO_db_JDBC ;
    private VoteDAO_JDBC voteDAO_jdbc ;

    //JPA
    private NominationDAO_JPA nominationDAO_jpa ;
    private AwardDAO_JPA awardDAO_jpa ;
    private VoteDAO_JPA voteDAO_jpa ;


    public void createNomination(){

        System.out.println("Please enter the nomination work : ");
        String responce = sc.nextLine();

        System.out.println("Please enter the obtained shares: ");
        double shares = sc.nextDouble();
        sc.nextLine();

        System.out.println("Choose the award categories in the list below: ");
        showTheAwardListMemory();
        String yourAwnser = sc.nextLine();

        awardDAOMemory.findAwardByName(yourAwnser);

        Nomination n = new Nomination(3, today,shares, responce, voteDAOMemory.readVote() ,awardDAOMemory.findAllAward());

        nominationDAOMemory.findAllNomination().add(n);
        System.out.println(nominate(awardDAOMemory.findAwardByName(yourAwnser), n ));
    }


    public void createNominationJDBC(){


        System.out.println("Please enter the nomination work : ");
        String responce = sc.nextLine();

        System.out.println("Please enter the obtained shares: ");
        double shares = sc.nextDouble();
        sc.nextLine();

        System.out.println("Choose the award categories in the list below: ");
        showTheAwardListJDBC();
        String yourAwnser = sc.nextLine();

        awardDAO_db_JDBC.findAwardByName(yourAwnser);


        Nomination n = new Nomination(defineMaxId(), this.today,shares, responce, voteDAO_jdbc.readVote(),awardDAO_db_JDBC.findAllAward());

        System.out.println(nominate(awardDAO_db_JDBC.findAwardByName(yourAwnser), n ));

        nominationDAO_DB_JDBC.createNomination(n);
    }

    public void createNominationJPA(){

        System.out.println("Please enter the nomination work : ");
        String responce = sc.nextLine();

        System.out.println("Please enter the obtained shares: ");
        double shares = sc.nextDouble();
        sc.nextLine();

        System.out.println("Choose the award categories in the list below: ");
        showTheAwardListJDBC();
        String yourAwnser = sc.nextLine();

       awardDAO_jpa.findAwardByName(yourAwnser);

        //Need fixe with jpa
        Nomination n = new Nomination(defineMaxId(), this.today, shares, responce,voteDAO_jpa.readVote(),awardDAO_jpa.findAllAward());

        nominationDAO_jpa.createNomination(n);

        System.out.println(nominate(awardDAO_jpa.findAwardByName(yourAwnser), n ));
    }

    public void showTheAwardListMemory() {
        //Getting award data
        List<Award> awards = this.awardDAOMemory.findAllAward();

        //Print the Data
        for (int i = 0; i <  awards.size() ; i++) {
            System.out.println(awards.get(i).getName());
        }
    }

    public void showTheAwardListJDBC(){

        //Getting the data
        List<Award> awards = awardDAO_db_JDBC.findAllAward();

        for(int i = 0 ; i < awards.size() ; i++ ){
            System.out.println("Awards categories: "+awards.get(i).getName());
        }
    }

    public void showTheAwardListJPA(){
        //Getting the data
        List<Award> awards = awardDAO_jpa.findAllAward();

        for(Award a : awards){
            System.out.println("Awards categories: " + a.getName());
        }
    }

    public int defineMaxId(){

        for(Award a :awardDAO_jpa.findAllAward()){
            if(this.maxID < a.getId()){
                this.maxID = a.getId();
            }
        }
        return this.maxID+1;
    }

    public String nominate(Award a, Nomination n){
        return "The Nominated work is " + n.getNominatedWork()+ " it have been nominated in the Award category " + a.getName();
    }

    public static void main(String[] args) {
        AwardControl test = new AwardControl();
        test.showTheAwardListJPA();
        test.showTheAwardListJDBC();
    }
}
