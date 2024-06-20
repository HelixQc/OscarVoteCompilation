package oscar.awardService.control;


import oscar.awardService.model.Award;
import oscar.awardService.model.AwardNomination;
import oscar.awardService.model.Nomination;
import oscar.awardService.model.Winner;
import oscar.awardService.persistence.JDBC.AwardDAO_DB_JDBC;
import oscar.awardService.persistence.JDBC.AwardNominationDAO_JDBC;
import oscar.awardService.persistence.JPA.AwardDAO_JPA;
import oscar.awardService.persistence.JPA.NominationDAO_JPA;
import oscar.awardService.persistence.Memory.AwardDAO_Memory;
import oscar.awardService.persistence.Memory.AwardNominationDAO_Memory;
import oscar.awardService.persistence.Memory.NominationDAO_Memory;
import oscar.awardService.persistence.JDBC.NominationDAO_DB_JDBC;
import oscar.electionServices.model.Vote;

import java.util.ArrayList;
import java.util.List;

/**
 * As a film historian,
 * I want to see all nomination
 * categories and respective winners,
 * so I can look for trends and
 * statistics in film awards.
 */

public class SeeNomination {

    public List<Winner> VoteFilterMemory() {
        NominationDAO_Memory nominationDAOMemory = new NominationDAO_Memory();
        AwardNominationDAO_Memory brigdesAwardNominationMemory = new AwardNominationDAO_Memory();
        AwardDAO_Memory awardDAOMemory = new AwardDAO_Memory();
        List<AwardNomination> bridgesAwardNomination = brigdesAwardNominationMemory.readAwardNomination();
        List<Nomination> nominations = nominationDAOMemory.findAllNomination();
        List<Award> awards = awardDAOMemory.findAllAward();
        double maxShare = 0 ;
        double totalShare = 0 ;
        Winner winnerTemp = new Winner();

        List<Winner> winners = new ArrayList<>();

        for (Award a : awards) {
            for (Nomination n : nominations) {
                for (AwardNomination an : bridgesAwardNomination) {
                    if (a.getId() == an.getAward_id()) {
                        if (an.getNomination_id() == n.getId()) {
                            totalShare = calculerShare(n);
                            if(maxShare < totalShare){
                                maxShare = totalShare;
                                winnerTemp = new Winner(a, n , totalShare);

                            }
                        }
                    }
                }
            }
            //List the winner
            winners.add(winnerTemp);
            maxShare = 0;
        }
        return winners;
    }

    public List<Winner> VoteFilterJDBC() {

        NominationDAO_DB_JDBC nominationDAO_db_jdbc = new NominationDAO_DB_JDBC();
        AwardNominationDAO_JDBC awardNominationDAO_jdbc = new AwardNominationDAO_JDBC();
        AwardDAO_DB_JDBC awardDAO_db_jdbc = new AwardDAO_DB_JDBC();

        List<AwardNomination> bridgesAwardNomination = awardNominationDAO_jdbc.readAwardNomination();
        List<Nomination> nominations = nominationDAO_db_jdbc.findAllNomination();
        List<Award> awards = awardDAO_db_jdbc.findAllAward();
        double maxShare = 0 ;
        double totalShare = 0 ;
        Winner winnerTemp = new Winner();
        List<Winner> winners = new ArrayList<>();

        for (Award a : awardDAO_db_jdbc.findAllAward()) {
            for (Nomination n : nominationDAO_db_jdbc.findAllNomination()) {
                for (AwardNomination an : awardNominationDAO_jdbc.readAwardNomination()) {
                    if (a.getId() == an.getAward_id()) {
                        if (an.getNomination_id() == n.getId()) {
                            totalShare = calculerShare(n);
                            if(maxShare < totalShare){
                                maxShare = totalShare;
                                winnerTemp = new Winner(a, n , totalShare);

                            }
                        }
                    }
                }
            }
            //List the winner
            winners.add(winnerTemp);
            maxShare = 0;
        }
        return winners;
    }

    public List<Winner> VoteFilterJPA() {

        NominationDAO_JPA nominationDAO_JPA = new NominationDAO_JPA();
        AwardNominationDAO_JDBC awardNominationDAO_jdbc = new AwardNominationDAO_JDBC();
        AwardDAO_JPA awardDAO_JPA = new AwardDAO_JPA();

        List<AwardNomination> bridgesAwardNomination = awardNominationDAO_jdbc.readAwardNomination();
        List<Nomination> nominations = nominationDAO_JPA.findAllNomination();
        List<Award> awards = awardDAO_JPA.findAllAward();
        double maxShare = 0 ;
        double totalShare = 0 ;
        Winner winnerTemp = new Winner();
        List<Winner> winners = new ArrayList<>();

        for (Award a : awards) {
            for (Nomination n : nominations) {
                for (AwardNomination an : bridgesAwardNomination) {
                    if (a.getId() == an.getAward_id()) {
                        if (an.getNomination_id() == n.getId()) {
                            totalShare = calculerShare(n);
                            if(maxShare < totalShare){
                                maxShare = totalShare;
                                winnerTemp = new Winner(a, n , totalShare);

                            }
                        }
                    }
                }
            }
            //List the winner
            winners.add(winnerTemp);
            maxShare = 0;
        }
        return winners;
    }

    public double calculerShare(Nomination nomination) {
        double myShares = 0;
        for (Vote vote : nomination.getVotes()) {
            if (nomination.getId() == vote.getNomination_id()) {
                myShares += vote.getShares();
            }
        }
        return myShares;
    }
    public void printeWinners(List<Winner> winners) {

        List<Award> awardsEmpty = new ArrayList<>();

        for (Winner winner : winners) {
            if(!awardsEmpty.contains(winner.getAward())){
                awardsEmpty.add(winner.getAward());
                System.out.println(winner);
                System.out.println();
            }
        }
    }
}




