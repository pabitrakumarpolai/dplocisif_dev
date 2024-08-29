package com.dplocisif.DPLOCISIF.repository.ProcedureRepository;

import com.dplocisif.DPLOCISIF.startupdto.ClaimRejectedItemKeyGenDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClaimRejectedProcedureRepository {
    @Autowired
    EntityManager entityManager;

    @Cacheable(value = "inspectionList", key = "#inspectionNo")
    public List<ClaimRejectedItemKeyGenDTO> getInspectionList(String inspectionNo) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("dcst_store_pg.get_inspection_list_pr", "ClaimRejectedItemKeyDTO");
        storedProcedureQuery.registerStoredProcedureParameter("p_inspection_no", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_refcur_inspection_list", void.class, ParameterMode.REF_CURSOR);

        storedProcedureQuery.setParameter("p_inspection_no", inspectionNo);

        return (List<ClaimRejectedItemKeyGenDTO>) storedProcedureQuery.getResultList();
    }
}
