package com.dplocisif.DPLOCISIF.repository.ProcedureRepository;

import com.dplocisif.DPLOCISIF.startupdto.StockAdjEmpDTO;
import com.dplocisif.DPLOCISIF.startupdto.StockAdjustmentDTO;
import com.dplocisif.DPLOCISIF.startupdto.StockDepoListDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class StockAdjEmpRepository {
    @Autowired
    EntityManager entityManager;
    @Cacheable(value = "allStockAdjustmentEmpList", key = "#adjustedBy")
    public List<StockAdjEmpDTO> getAllEmpList(long adjustedBy) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("DCST_STORE_PG.get_stk_adj_emp_list_pr", "stockAdjEmpDTOMapping");

        storedProcedureQuery.registerStoredProcedureParameter("p_adjusted_by", long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_refcur_stk_adj_emp_list", void.class, ParameterMode.REF_CURSOR);

        storedProcedureQuery.setParameter("p_adjusted_by", adjustedBy);

        return storedProcedureQuery.getResultList();
    }

    @Cacheable(value = "allStockDepoList", key = "#depoCode")
    public List<StockDepoListDTO> getAllDepoList(String depoCode){
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("DCST_STORE_PG.get_depo_list_pr", "stockDepoListDTOMapping");

        storedProcedureQuery.registerStoredProcedureParameter("p_depo_code",String.class,ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_refcur_depo_list",void.class,ParameterMode.REF_CURSOR);

        storedProcedureQuery.setParameter("p_depo_code",depoCode);

        return storedProcedureQuery.getResultList();
    }

    @Cacheable(value = "allStockAdjustmentItemList", key = "#depoCode")
    public List<StockAdjustmentDTO> getAllItemList(String depoCode) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("DCST_STORE_PG.get_stk_adj_item_list_pr", "stockAdjustment");

        storedProcedureQuery.registerStoredProcedureParameter("p_depo_code", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_refcur_stk_adj_item_list", void.class, ParameterMode.REF_CURSOR);

        storedProcedureQuery.setParameter("p_depo_code", depoCode);

        return storedProcedureQuery.getResultList();
    }
}
