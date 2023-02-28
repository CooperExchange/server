/*
ECE-366: CooperExchange
Description: Abstract class for Data Access Objects (DAOs).
 */


package CooperExchangeJDBC.Util;

import java.sql.*;
import java.util.List;
import java.sql.Connection;

public abstract class DataAccessObject <T extends DataTransferObject> {
    //attributes
    protected final Connection connection;

    //constructor
    public DataAccessObject (Connection connection){
        super(); //accesses parent constructor
        this.connection = connection;
    }

    //abstract method
    public abstract T findById(long id);
}
