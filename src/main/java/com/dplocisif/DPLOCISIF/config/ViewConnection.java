package com.dplocisif.DPLOCISIF.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

@Component
public class ViewConnection {
    @Autowired
    DataSource dataSource;
    private final Logger logger = Logger.getLogger(ProcedureConnectionCall.class.getName());
    private Statement statement = null;

    public ResultSet getConnectionWithResultSet(String view) {
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            statement = conn.createStatement();
            rs = statement.executeQuery(view);

        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return rs;
    }

    public void freeConnection() {
        try {
            Connection connection = statement.getConnection();
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (SQLException exception) {
            logger.info(exception.getMessage());
        }
    }
}
