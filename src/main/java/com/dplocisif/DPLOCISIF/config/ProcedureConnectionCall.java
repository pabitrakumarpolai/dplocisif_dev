package com.dplocisif.DPLOCISIF.config;

import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

@Component
public class ProcedureConnectionCall {
    @Autowired
    DataSource dataSource;
    private final Logger logger = Logger.getLogger(ProcedureConnectionCall.class.getName());
    public CallableStatement getConnection(String procedure) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = dataSource.getConnection();
            String logSQL = procedure;
            cstmt = conn.prepareCall(logSQL);

        } catch (Exception e) {
            logger.info(e.getMessage());
            try {
                Assert.notNull(conn, "Connection Is Not Established");
                conn.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return cstmt;
    }
    public void closeAllConnection(Connection conn, CallableStatement cstmt, ResultSet rs) {
        try {
            if (rs != null){
                rs.close();
                rs = null;
            }
            if (cstmt != null) {
                cstmt.close();
                cstmt = null;
            }
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeAllConnection(Connection conn, CallableStatement cstmt) {
        try {
            if (cstmt != null){
                cstmt.close();
                cstmt = null;
            }
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeAllConnection(CallableStatement cstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (cstmt != null){
                cstmt.close();
                cstmt = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
