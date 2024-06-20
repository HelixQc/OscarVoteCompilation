package oscar.electionServices.persistence.JDBC;

import oscar.awardService.persistence.ConnectionDAO;
import oscar.electionServices.model.Elector;
import oscar.electionServices.persistence.IElectorDAO;
import oscar.electionServices.persistence.QueryBox;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ElectorDAO_JDBC extends ConnectionDAO implements IElectorDAO {

    public ElectorDAO_JDBC(){
        super();
    }

    @Override
    public void createElector(Elector e) {

        try{

            PreparedStatement ps = this.connection.prepareStatement(QueryBox.CreateElector);
            ps.setDouble(1,e.getWeight());
            ps.setString(2, e.getName());
            ps.executeQuery();

            System.out.println("The Elector "+ e.getName() + " have been added with succes");

        }catch (SQLException sql){
            throw  new RuntimeException(sql);
        }
    }

    @Override
    public void deleteElector(Elector e) {
        //***NEED A QUERYBOX***//
        //delete***
        //e.getParam of e for each column in the database
        readElector().remove(e);
        System.out.println("The Elector have been delete with succes");
    }

    @Override
    public List<Elector> readElector() {
        List<Elector> electors = new ArrayList<>();
        try{
            Statement st = this.connection.createStatement();
            ResultSet result = st.executeQuery(QueryBox.FindAllElectors);

            while(result.next()){
                Integer electorID = result.getInt("id");
                Double shareWeight = result.getDouble("weight");
                String name = result.getString("name");
                Elector elector = new Elector(electorID, shareWeight, name);
                electors.add(elector);
            }

        }catch (SQLException sql){
            throw new RuntimeException(sql);
        }
        return electors;
    }

    @Override
    public Elector updateElector(Elector e) {
        return null;
    }

    public Elector findElectorByWeight(int weight){

        try{
            Statement st = this.connection.createStatement();
            ResultSet result = st.executeQuery(QueryBox.FindElectorByWeight + weight);

            if(result.next()){
                Integer id = result.getInt("id");
                Double sharesWeight = result.getDouble("weight");
                String name = result.getString("name");

                return  new Elector(id,sharesWeight,name);
            }

        }catch (SQLException sql){
            throw new RuntimeException(sql);
        }
        return null;
    }

}
