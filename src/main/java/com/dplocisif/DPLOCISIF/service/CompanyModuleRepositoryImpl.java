package com.dplocisif.DPLOCISIF.service;

import com.dplocisif.DPLOCISIF.config.ProcedureConnectionCall;
import com.dplocisif.DPLOCISIF.repository.CompanyModuleRepository;
import com.dplocisif.DPLOCISIF.startupdto.CompanyModuleInfoDTO;
import com.dplocisif.DPLOCISIF.transformer.CompanyModuleTransformer;
import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * CompanyModuleRepositoryImpl implements the CompanyModuleRepository interface.
 * It provides methods to fetch company details from the database.
 */

@Service
public class CompanyModuleRepositoryImpl implements CompanyModuleRepository {
    @Autowired
    ProcedureConnectionCall connectionCall;
    private final Logger logger = Logger.getLogger(CompanyModuleRepositoryImpl.class.getName());

    /**
     * Retrieves company details from the database.
     * @return A list of CompanyModuleInfoDTO objects containing company details.
     */

    @Override
    public List<CompanyModuleInfoDTO> getDcpyCompanyDetails() {
        List<CompanyModuleInfoDTO> result = new ArrayList<>();
        CallableStatement cstmt = connectionCall.getConnection("{call DCPY_UTILITY_PG.get_dcpy_company_pr(?)}");
        ResultSet rs = null;
        try {
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                result.add(CompanyModuleTransformer.transformCompany(rs));
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } finally {
            try {
                connectionCall.closeAllConnection(cstmt.getConnection(), cstmt, rs);
            } catch (SQLException exception) {
                logger.info(exception.getMessage());
            }
        }
        return result;
    }

}
