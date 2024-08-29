package com.dplocisif.DPLOCISIF.DAO;

import com.dplocisif.DPLOCISIF.config.ProcedureConnectionCall;
import com.dplocisif.DPLOCISIF.repository.ProcedureRepository.ClaimRejectedProcedureRepository;
import com.dplocisif.DPLOCISIF.startupdto.ClaimRejectedItemKeyGenDTO;
import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ClaimRejectedItemDAO {
    @Autowired
    ProcedureConnectionCall procedureConnectionCall;
//    public List<ClaimRejectedItemKeyGenDTO> getInspectionList(String inspectionNo) throws SQLException {
//        return claimRejectedProcedureRepository.getInspectionList(inspectionNo);
//        CallableStatement callableStatement = null;
//        List<ClaimRejectedItemKeyGenDTO> claimRejectedItemKeyGenDTOList=null;
//        ResultSet rs = null;
//        try {
//            callableStatement = procedureConnectionCall.getConnection("{call dcst_store_pg.get_inspection_list_pr(?, ?)}");
//
//            callableStatement.setString(1, inspectionNo);
//            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
//            callableStatement.execute();
//            rs = (ResultSet) callableStatement.getObject(2);
//            claimRejectedItemKeyGenDTOList= new ArrayList<>();
//            while (rs.next()) {
//                claimRejectedItemKeyGenDTOList.add(ClaimRejectedItemKeyGenDTO.builder()
//                        .inspectionNo(rs.getString("INSPECTION_NO"))
//                        .inspectionDate(rs.getString("INSPECTION_DATE"))
//                        .challanNo(rs.getString("CHALLAN_NO"))
//                        .qtyAccepted(rs.getString("QTY_ACCEPTED"))
//                        .qtyRejected(rs.getString("QTY_REJECTED"))
//                        .discrepancyNoted(rs.getString("DISCREPANCY_NOTED"))
//                        .itemCode(rs.getString("ITEM_CODE"))
//                        .folioNo(rs.getString("FOLIO_NO"))
//                        .itemDescription(rs.getString("ITEM_DESCRIPTION"))
//                        .unitCode(rs.getString("UNIT_CODE"))
//                        .unitDescription(rs.getString("UNIT_DESCRIPTION"))
//                        .build());
//            }
//        } catch (SQLException exception) {
//            throw new RuntimeException(exception);
//        } finally {
//            if (callableStatement != null)procedureConnectionCall.closeAllConnection(callableStatement.getConnection(), callableStatement);
//        }
//        return claimRejectedItemKeyGenDTOList;
//    }

    public String getCliamCode(String prefix, Date date ) throws SQLException {
        CallableStatement callableStatement = null;
        String result = "";
        try {
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            callableStatement = procedureConnectionCall.getConnection("{? = call dcst_utility_pg.PRIMARY_KEY_GEN_FN(?, ?, ?, ?, ?)}");
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.setString(2, prefix);
            callableStatement.setString(3, "T_DCST_T_CLAIM_REJ_ITEM");
            callableStatement.setString(4, "CLAIM_NO");
            callableStatement.setInt(5, 6);
            callableStatement.setDate(6, sqlDate);
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
