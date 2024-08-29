package com.dplocisif.DPLOCISIF.repository.ProcedureRepository;

import com.dplocisif.DPLOCISIF.startupdto.TransactionCodeDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionCodeRepository {
    @Autowired
    EntityManager entityManager;

    public List<TransactionCodeDTO> getTransactionCodeList(Long transactionNumber) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("DCST_STORE_PG.get_transaction_code_list_pr", "getTransactionCode");
        storedProcedureQuery.registerStoredProcedureParameter("p_transaction_code", Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_refcur_transaction_code_list", void.class, ParameterMode.REF_CURSOR);

        storedProcedureQuery.setParameter("p_transaction_code", transactionNumber);

        List<TransactionCodeDTO> result = storedProcedureQuery.getResultList();
        return result;
    }
}
