package oscar.awardService.view;

import oscar.awardService.control.SeeNomination;
import oscar.awardService.data.NominationRepository;
import oscar.awardService.model.Nomination;
import oscar.awardService.persistence.NominationDAO_DB;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class NominationUI {

    Scanner sc = new Scanner(System.in);
    SeeNomination overWatch = new SeeNomination();

    public void startNominationUI() {

        System.out.println("Please type in a number between 1 and 3 to navigate in the option below");
        System.out.println("option 1 : show the nomination list");
        System.out.println("option 2 : UserStory");
        System.out.println("option 3 : next step");

        do{
            try{
                int awnser = sc.nextInt();
                if(awnser==1){
                    showAllNomination();
                    break;
                } else if (awnser==2) {
                    userStoryController();
                    userStoryControllerJDBC();
                    break;
                } else if (awnser == 3) {
                    break;
                }else{
                    System.out.println("Please enter either 1, 2 or 3");
                }
            }catch (InputMismatchException em1){
                System.out.println("Please enter a proper number: ");
                sc.next();
            }
        }while(true);
    }

    public void showAllNomination(){

        // Getting all nominations
        List<Nomination> allNominations = NominationRepository.getInstance().getAllNominations();
        System.out.println("All Nominations :");
        System.out.println("---------------------------------------------");

        for(int i = 0 ; i < allNominations.size(); i++) {
            System.out.println("nomination: "+ i);
            System.out.println("Year: "+allNominations.get(i).getYear());
            System.out.println("Nominated Work: "+allNominations.get(i).getNominatedWork());
            System.out.println("Obtained Shares: "+allNominations.get(i).getObtainedShares());
            System.out.println("---------------------------------------------");
        }
    }


    public void userStoryController(){
        System.out.println("---------------------------------------------");
        for(int i = 0 ; i < overWatch.consultNominationInMemoryRepository().size(); i++){
            System.out.println("ID: "+overWatch.consultNominationInMemoryRepository().get(i).getId());
            System.out.println("Year: "+overWatch.consultNominationInMemoryRepository().get(i).getYear());
            System.out.println("Obtained Shares: "+overWatch.consultNominationInMemoryRepository().get(i).getObtainedShares());
            System.out.println("Nominated Work: "+overWatch.consultNominationInMemoryRepository().get(i).getNominatedWork());
            System.out.println("---------------------------------------------");
            System.out.println("---Awards---");
            for(int j = 0 ; j < overWatch.consultNominationInMemoryRepository().get(i).getAwards().size(); j++)
            System.out.println(overWatch.consultNominationInMemoryRepository().get(i).getAwards().get(j).getName());
            System.out.println("---------------------------------------------");
        }

    }

    public void userStoryControllerJDBC(){
        System.out.println("---------------------------------------------");
        for(int i = 0 ; i < overWatch.consultNominationJDBC().size(); i++){
            System.out.println("ID: "+overWatch.consultNominationJDBC().get(i).getId());
            System.out.println("Year: "+overWatch.consultNominationJDBC().get(i).getYear());
            System.out.println("Obtained Shares: "+overWatch.consultNominationJDBC().get(i).getObtainedShares());
            System.out.println("Nominated Work: "+overWatch.consultNominationJDBC().get(i).getNominatedWork());
            System.out.println("---------------------------------------------");
            System.out.println("---Awards---");
            for(int j = 0 ; j < overWatch.consultNominationJDBC().get(i).getAwards().size(); j++)
                System.out.println(overWatch.consultNominationJDBC().get(i).getAwards().get(j).getName());
            System.out.println("---------------------------------------------");
        }

    }

    public void showAllNominationJDBC(){
        NominationDAO_DB daoDb = new NominationDAO_DB();
        List<Nomination> allNominations =  daoDb.findAllNomination();
        System.out.println("All Nominations :");
        System.out.println("---------------------------------------------");
        for(int i = 0 ; i < allNominations.size(); i ++){
            System.out.println("Id; "+ allNominations.get(i).getId());
            System.out.println("Year; "+ allNominations.get(i).getYear());
            System.out.println("Obtained Shares; "+ allNominations.get(i).getObtainedShares());
            System.out.println("Nominated Work; "+ allNominations.get(i).getNominatedWork());
            System.out.println("---------------------------------------------");
        }
    }
}