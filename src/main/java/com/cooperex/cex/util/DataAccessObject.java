package com.cooperex.cex.util;
import java.sql.*;
import java.util.List;

public abstract class DataAccessObject  {
    protected final Connection connection;

    public DataAccessObject(Connection connection){
        super();
        this.connection = connection;
    }
}
