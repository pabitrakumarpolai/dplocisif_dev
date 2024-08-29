package com.dplocisif.DPLOCISIF.repository.ProcedureRepository;

import com.dplocisif.DPLOCISIF.startupdto.ChallanDetailsHeaderDTO;
import com.dplocisif.DPLOCISIF.startupdto.PoListDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChallanHeaderRepository {
    @Autowired
    EntityManager entityManager;

    @Cacheable(value = "allChllanListInspection", key = "#challanNumber")
    public List<ChallanDetailsHeaderDTO> getAllChallanListInspection(String challanNumber) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("DCST_STORE_PG.get_challan_list_inspection_pr", "challanDetailsHeader");

        storedProcedureQuery.registerStoredProcedureParameter("p_challan_no", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_refcur_challan_list", void.class, ParameterMode.REF_CURSOR);

        storedProcedureQuery.setParameter("p_challan_no", challanNumber);

        return storedProcedureQuery.getResultList();
    }

    public List<PoListDTO> getPoListPr(String depoCode) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("DCST_STORE_PG.get_po_list_pr", "getPoListPr");

        storedProcedureQuery.registerStoredProcedureParameter("p_depo_code", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_refcur_po_list", void.class, ParameterMode.REF_CURSOR);

        storedProcedureQuery.setParameter("p_depo_code", depoCode);

        List<PoListDTO> result = storedProcedureQuery.getResultList();
        return result;
    }
}