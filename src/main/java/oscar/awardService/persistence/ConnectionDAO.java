package oscar.awardService.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *Here is the connection to the database.
 * the data is stored on alwaydata.com
 * every info will be in this DAO.
 * Use MariaDB when you start the connection.
 */

public abstract class ConnectionDAO {

    //connection to Database is here!!!!
    protected static final String DB_HOST = "The Host URL Here";
    protected static final String DB_NAME ="Your dbName";
    protected static final String DB_USER = "Your username";
    protected static final String DB_PASSWORD = "Your Password";
    protected static final String DB_URL = DB_HOST + DB_NAME;
    protected Connection connection ;

    public ConnectionDAO(){
        try{
                this.connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
