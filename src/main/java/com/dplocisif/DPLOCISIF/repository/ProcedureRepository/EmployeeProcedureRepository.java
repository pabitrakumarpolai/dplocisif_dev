package com.dplocisif.DPLOCISIF.repository.ProcedureRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeProcedureRepository {

    @Autowired
    EntityManager entityManager;

    /**
     * Calls a stored procedure to create a new login entry in the database.
     * @param companyId ID of the company.
     * @param loginName User's login name.
     * @param ngs User's NGS.
     * @param password User's password.
     * @param roleId ID of the user's role.
     * @return The ID of the created login entry.
     */

    public Integer createLogin(Long companyId, String loginName, String ngs, String password, Long roleId) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("DCST_CREATE_LOGIN");
        // Register parameters
        storedProcedureQuery.registerStoredProcedureParameter("p_company_id", Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_login_name", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_ngs", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_password", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_role_id", Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("o_login_id", Integer.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("p_company_id", companyId);
        storedProcedureQuery.setParameter("p_login_name", loginName);
        storedProcedureQuery.setParameter("p_ngs", ngs);
        storedProcedureQuery.setParameter("p_password", password);
        storedProcedureQuery.setParameter("p_role_id", roleId);

        storedProcedureQuery.execute();

        return (Integer) storedProcedureQuery.getOutputParameterValue("o_login_id");
    }
}
