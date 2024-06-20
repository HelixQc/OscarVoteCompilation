package oscar.awardService.persistence.JDBC;

import oscar.awardService.model.Award;
import oscar.awardService.model.Nomination;
import oscar.awardService.persistence.ConnectionDAO;
import oscar.awardService.persistence.INominationDAO;
import oscar.awardService.persistence.QueryBox;
import oscar.electionServices.persistence.JDBC.VoteDAO_JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NominationDAO_DB_JDBC extends ConnectionDAO implements INominationDAO {

    VoteDAO_JDBC voteDAO_jdbc = new VoteDAO_JDBC();
    AwardDAO_DB_JDBC awardDAO_db = new AwardDAO_DB_JDBC();
    List<Award> awards = awardDAO_db.findAllAward();
    int maxID = 0;

    public NominationDAO_DB_JDBC(){
        super();
    }

    @Override
    public Nomination findNominationById(int id) {
        try {
            Statement st = this.connection.createStatement();
            ResultSet result = st.executeQuery(QueryBox.FindNominationById + id);
            if(result.next()) {

                int year = result.getInt("year");
                int ObtainedShared = result.getInt("obtainedShares");
                String nominatedWork = result.getString("nominatedWork");
                int fk = result.getInt("awardFK");

                // objectify the loaded information => unmarshalling
                return new Nomination(id, year, ObtainedShared ,nominatedWork,voteDAO_jdbc.readVote(), awards);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    } // findNominationById

    @Override
    public void createNomination(Nomination n) {

        try{
            PreparedStatement ps = this.connection.prepareStatement(QueryBox.CreateNomination);
            //ps.setLong(1,MaxId());
            ps.setLong(1, n.getYear());
            ps.setDouble(2, n.getObtainedShares());
            ps.setString(3, n.getNominatedWork());
            //ps.setInt(4, );
            ps.executeUpdate();
            System.out.println("The nomination: "+ n.getNominatedWork() + " have been added with success");

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteNomination(Nomination n) {

    }

    @Override
    public List<Nomination> findAllNomination() {
        List<Nomination> nominations = new ArrayList<>();
        try {
            Statement st = this.connection.createStatement();
            ResultSet result = st.executeQuery(QueryBox.FindAllNomination);

            while (result.next()) {

                int id = result.getInt("id");
                int year = result.getInt("year");
                int obtainedShares = result.getInt("obtainedShares");
                String nominatedWork = result.getString("nominatedWork");
                //int fk = result.getInt("awardFK");

                // Create Nomination object and add it to the list
                Nomination nomination = new Nomination(id, year, obtainedShares, nominatedWork, voteDAO_jdbc.readVote(),awards);
                nominations.add(nomination);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nominations;
    }

    @Override
    public Nomination UpdateNomination(Nomination n) {
        return null;
    }



}



