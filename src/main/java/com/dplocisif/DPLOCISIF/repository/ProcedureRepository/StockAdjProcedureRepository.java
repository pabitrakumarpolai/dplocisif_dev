package com.dplocisif.DPLOCISIF.repository.ProcedureRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class StockAdjProcedureRepository {
    @Autowired
    EntityManager entityManager;

    public void createStockAdjustment(String tranType,String depoCode,String itemCode,String debitCreditFlag,Long adjustedQty,Long stockAdjSrlNo) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("DCST_STORE_PG.upd_item_stock_for_stk_adj_pr ");
        storedProcedureQuery.registerStoredProcedureParameter("P_tran_type", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_depo_code", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_item_code", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_debit_credit_flag", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_adjusted_qty", Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_stock_adj_srl_no", Long.class, ParameterMode.IN);

        storedProcedureQuery.setParameter("P_tran_type", tranType);
        storedProcedureQuery.setParameter("p_depo_code", depoCode);
        storedProcedureQuery.setParameter("p_item_code", itemCode);
        storedProcedureQuery.setParameter("p_debit_credit_flag", debitCreditFlag);
        storedProcedureQuery.setParameter("p_adjusted_qty", adjustedQty);
        storedProcedureQuery.setParameter("p_stock_adj_srl_no", stockAdjSrlNo);

        storedProcedureQuery.execute();
    }

}
