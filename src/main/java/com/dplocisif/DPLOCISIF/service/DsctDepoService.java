package com.dplocisif.DPLOCISIF.service;

import com.dplocisif.DPLOCISIF.exception.LoginIdMissingException;
import com.dplocisif.DPLOCISIF.model.DsctDepo;
import com.dplocisif.DPLOCISIF.repository.DsctDepoRepository;
import com.dplocisif.DPLOCISIF.repository.ProcedureRepository.StockAdjEmpRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * DsctDepoService provides methods for managing depot operations.
 */

@Service
public class DsctDepoService {
    @Autowired
    DsctDepoRepository depoRepository;
    @Autowired
    StockAdjEmpRepository repository;

    private ObjectMapper objectMapper = new ObjectMapper();

    private HashMap<String, List<DsctDepo>> descriptionBasedOnMap;
    private HashMap<Long, DsctDepo> depoCodeBasedOnMap;

    /**
     * Adds a new depot.
     * @param object The JSON object containing depot information.
     * @param result A Map to store the operation result.
     * @throws JSONException If there is an error in JSON processing.
     * @throws JsonProcessingException If there is an error in JSON processing.
     */

    public void addDepo(String object, Map<String, Object> result) throws JSONException, JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JSONObject jsonObject = new JSONObject(object);
        DsctDepo dsctDepo = objectMapper.readValue(object, DsctDepo.class);
        dsctDepo.setDepoDescription(dsctDepo.getDepoDescription().toUpperCase());

        long longinIDDForCreatedBy = jsonObject.has("loginId") ? jsonObject.getLong("loginId") : 0;
        if (longinIDDForCreatedBy == 0) throw new LoginIdMissingException("Login id Not Present");

        Optional<DsctDepo> optionalDsctDepo = depoRepository.findByDepoCode(dsctDepo.getDepoCode());

        if (optionalDsctDepo.isEmpty()) {
            long currentTimesInMillis = System.currentTimeMillis();
            Date currentDate = new Date(currentTimesInMillis);
            dsctDepo.setCreatedBy(longinIDDForCreatedBy);
            dsctDepo.setCreatedOn(currentDate);

            DsctDepo savedDepo = depoRepository.save(dsctDepo);

            result.put("success", savedDepo);
        } else {
            result.put("error", "Depo Code is Already Present!!");
        }
    }

    /**
     * Updates an existing depot.
     * @param object The JSON object containing updated depot information.
     * @param result A Map to store the operation result.
     * @throws JsonProcessingException If there is an error in JSON processing.
     * @throws JSONException If there is an error in JSON processing.
     */

    public void updateDepo(String object, Map<String, Object> result) throws JsonProcessingException, JSONException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JSONObject jsonObject = new JSONObject(object);
        DsctDepo dsctDepo = objectMapper.readValue(object, DsctDepo.class);
        dsctDepo.setDepoDescription(dsctDepo.getDepoDescription().toUpperCase());

        long longinIDDForModifiedBy = jsonObject.has("loginId") ? jsonObject.getLong("loginId") : 0;
        if (longinIDDForModifiedBy == 0) throw new LoginIdMissingException("Login id Not Present");
        long currentTimesInMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimesInMillis);

        Optional<DsctDepo> optionalDsctDepo = depoRepository.findByDepoCode(dsctDepo.getDepoCode());

        if (!optionalDsctDepo.isEmpty()) {
            DsctDepo olderDepo = optionalDsctDepo.get();
            olderDepo.setDepoDescription(dsctDepo.getDepoDescription());
            olderDepo.setModuleFlag(dsctDepo.getModuleFlag());
            olderDepo.setModifiedBy(longinIDDForModifiedBy);
            olderDepo.setModifiedOn(currentDate);

            DsctDepo updatedDepo = depoRepository.save(olderDepo);

            result.put("success", updatedDepo);
        } else {
            result.put("error", "Depo Code Is Wrong");
        }
    }

    /**
     * Searches depots by depot code or description.
     * @param searchPrefix The JSON object containing search criteria.
     * @param result A Map to store the operation result.
     * @throws JSONException If there is an error in JSON processing.
     */

    public void searchByDeptCodeOrDescription(String searchPrefix, Map<String, Object> result) throws JSONException {
        JSONObject jsonObject = new JSONObject(searchPrefix);
        String prefixDescription = jsonObject.has("description") ? ((String) jsonObject.get("description")).toUpperCase() : null;
        String prefixCode = jsonObject.has("code") ? (jsonObject.getString("code")) : null;

        List<DsctDepo> searchResult = depoRepository.findDepo(prefixCode, prefixDescription);
        result.put("success", searchResult);
    }

    /**
     * Retrieves all depots.
     * @param result A Map to store the operation result.
     */

    public void getAllDepos(Map<String, Object> result) {
        List<DsctDepo> depos = depoRepository.findAll();
        result.put("success", depos);
    }
}
