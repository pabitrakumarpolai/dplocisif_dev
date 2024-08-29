package com.dplocisif.DPLOCISIF.DAO;

import com.dplocisif.DPLOCISIF.config.ProcedureConnectionCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

@Service
public class ItemDAO {
    @Autowired
    ProcedureConnectionCall procedureConnectionCall;
    public String getItemCode(String depoCode, String groupCode) throws SQLException {
        CallableStatement callableStatement = null;
        String result = "";
        try {
            callableStatement = procedureConnectionCall.getConnection("{? = call DCST_STORE_PG.gen_item_code(?, ?)}");
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.setString(2, depoCode);
            callableStatement.setString(3, groupCode);
            callableStatement.execute();
            result = callableStatement.getString(1);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            if (callableStatement != null)procedureConnectionCall.closeAllConnection(callableStatement.getConnection(), callableStatement);
        }
        return result;
    }
}
