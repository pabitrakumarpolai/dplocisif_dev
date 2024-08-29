package com.dplocisif.DPLOCISIF.repository.ProcedureRepository;

import com.dplocisif.DPLOCISIF.startupdto.ChallanChildDetailsDTO;
import com.dplocisif.DPLOCISIF.startupdto.JobDTO;
import com.dplocisif.DPLOCISIF.startupdto.PoItemListPrDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChallanChildDetailsRepository {
    @Autowired
    EntityManager entityManager;

    @Cacheable(value = "challanListInspectionPr", key = "#challanNumber")
    public List<ChallanChildDetailsDTO> getAllChallanListInspectionPr(String challanNumber, Long challanSerialNumber) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("DCST_STORE_PG.get_challan_del_list_inspec_pr", "ChallanChildDetailsDTOMapping");

        storedProcedureQuery.registerStoredProcedureParameter("p_challan_no", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_challan_srl_no", Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_refcur_challan_det_list", void.class, ParameterMode.REF_CURSOR);


        storedProcedureQuery.setParameter("p_challan_no", challanNumber);
        storedProcedureQuery.setParameter("p_challan_srl_no", challanSerialNumber);

        List<ChallanChildDetailsDTO> result = storedProcedureQuery.getResultList();
        return result;
    }

    @Cacheable(value = "poItemListPr", key = "#poNumber + #ordItmsrlNo")
    public List<PoItemListPrDTO> getOrderedSrlNo(String poNumber, Long ordItmsrlNo) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("DCST_STORE_PG.get_po_item_list_pr", "GetPoItemListPr");

        storedProcedureQuery.registerStoredProcedureParameter("p_ord_ref_no", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_ord_itmsrl_no", Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_refcur_po_item_list", void.class, ParameterMode.REF_CURSOR);


        storedProcedureQuery.setParameter("p_ord_ref_no", poNumber);
        storedProcedureQuery.setParameter("p_ord_itmsrl_no", ordItmsrlNo);

        List<PoItemListPrDTO> result = storedProcedureQuery.getResultList();
        return result;
    }

    @Cacheable(value = "getJobListPr", key = "#depoCode + #jobCode")
    public List<JobDTO> getJobListPr(String depoCode, Long jobCode) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("DCST_STORE_PG.get_job_list_pr", "GetJobListPr");

        storedProcedureQuery.registerStoredProcedureParameter("p_depo_code", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_job_code", Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_refcur_job_list", void.class, ParameterMode.REF_CURSOR);


        storedProcedureQuery.setParameter("p_depo_code", depoCode);
        storedProcedureQuery.setParameter("p_job_code", jobCode);

        List<JobDTO> result = storedProcedureQuery.getResultList();
        return result;
    }
}
