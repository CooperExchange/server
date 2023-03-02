/*
ECE-366: CooperExchange
Description: Abstract class for Data Access Objects (DAOs).
 */

package com.cooperex.cex.util;
import java.sql.*;
import java.util.List;

public abstract class DataAccessObject <T extends DataTransferObject> {
    //attributes
    protected final Connection connection;

    //constructor
    public DataAccessObject (Connection connection){
        super(); //accesses parent constructor
        this.connection = connection;
    }

    //abstract method
//    public abstract T findById(long id);
}
