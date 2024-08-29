
package com.dplocisif.DPLOCISIF.service;

import com.dplocisif.DPLOCISIF.dplApplicationStartup.ApplicationStartup;
import com.dplocisif.DPLOCISIF.exception.LoginIdMissingException;
import com.dplocisif.DPLOCISIF.model.UnitCodeMasterModule;
import com.dplocisif.DPLOCISIF.repository.UnitCodeMasterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Service class for managing unit code master data.
 */

@Service
public class UnitCodeMasterService {
    @Autowired
    UnitCodeMasterRepository unitCodeMasterRepository;
    @Autowired
    ApplicationStartup applicationStartup;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private HashMap<String, List<UnitCodeMasterModule>> mapBasedOnUnitCodeDescription;
    private HashMap<Long, UnitCodeMasterModule> mapBasedUnitCode;

    /**
     * Adds a new unit code master record.
     *
     * @param object The JSON object containing the unit code details.
     * @param result The map to store the operation result.
     * @throws JSONException If there is an error in JSON processing.
     * @throws JsonProcessingException If there is an error in JSON processing.
     */

    public void addUnitCode(String object, Map<String, Object> result) throws JSONException, JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JSONObject jsonObject = new JSONObject(object);
        UnitCodeMasterModule unitCodeMasterModule = objectMapper.readValue(object, UnitCodeMasterModule.class);

        long longinIDForCreatedBy = jsonObject.has("loginId") ? jsonObject.getLong("loginId") : 0;
        if (longinIDForCreatedBy == 0) throw new LoginIdMissingException("Login id Not Present");

        long currentTimesInMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimesInMillis);
        unitCodeMasterModule.setCreatedBy(longinIDForCreatedBy);
        unitCodeMasterModule.setCreatedOn(currentDate);

        UnitCodeMasterModule saveUnitCodeMaster = unitCodeMasterRepository.save(unitCodeMasterModule);

        result.put("success", "Data Saved Successfully");
        mapBasedOnUnitCodeDescription = applicationStartup.getMapBasedOnUnitCodeDescription();
        mapBasedUnitCode = applicationStartup.getMapBasedUnitCode();
        List<UnitCodeMasterModule> unitCodeMasterModuleList = mapBasedOnUnitCodeDescription.containsKey(saveUnitCodeMaster.getUnitDescription()) ? mapBasedOnUnitCodeDescription.get(saveUnitCodeMaster.getUnitDescription()) : new ArrayList<>();
        unitCodeMasterModuleList.add(saveUnitCodeMaster);
        mapBasedOnUnitCodeDescription.put(unitCodeMasterModule.getUnitDescription(), unitCodeMasterModuleList);
        mapBasedUnitCode.put(saveUnitCodeMaster.getUnitCode(), unitCodeMasterModule);
    }

    /**
     * Updates an existing unit code master record.
     *
     * @param object The JSON object containing the updated unit code details.
     * @param result The map to store the operation result.
     * @throws JSONException If there is an error in JSON processing.
     * @throws JsonProcessingException If there is an error in JSON processing.
     */

    public void updateUnitCode(String object, Map<String, Object> result) throws JsonProcessingException, JSONException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        if (CollectionUtils.isEmpty(mapBasedOnUnitCodeDescription))
            mapBasedOnUnitCodeDescription = applicationStartup.getMapBasedOnUnitCodeDescription();
        if (CollectionUtils.isEmpty(mapBasedUnitCode))
            mapBasedUnitCode = applicationStartup.getMapBasedUnitCode();

        JSONObject jsonObject = new JSONObject(object);
        UnitCodeMasterModule unitCodeMasterModule = objectMapper.readValue(object, UnitCodeMasterModule.class);

        long longinIDForModifiedBy = jsonObject.has("loginId") ? jsonObject.getLong("loginId") : 0;
        if (longinIDForModifiedBy == 0) throw new LoginIdMissingException("Login id Not Present");

        long currentTimesInMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimesInMillis);

        Optional<UnitCodeMasterModule> optionalUnitCode = unitCodeMasterRepository.findById(unitCodeMasterModule.getUnitCode());
        UnitCodeMasterModule olderUnitCodeMaster = optionalUnitCode.orElse(null);

        List<UnitCodeMasterModule> olderUnitCodeMasterList = mapBasedOnUnitCodeDescription.get(olderUnitCodeMaster.getUnitDescription());
        olderUnitCodeMasterList.removeIf(uCode -> uCode.getUnitDescription().equals(olderUnitCodeMaster.getUnitDescription()) && uCode.getUnitCode() == olderUnitCodeMaster.getUnitCode());
        if (!mapBasedOnUnitCodeDescription.get(olderUnitCodeMaster.getUnitDescription()).isEmpty()) {
            mapBasedOnUnitCodeDescription.remove(olderUnitCodeMaster.getUnitDescription());
        }
        mapBasedUnitCode.remove(olderUnitCodeMaster.getUnitCode());

        olderUnitCodeMaster.setUnitDescription(unitCodeMasterModule.getUnitDescription());
        olderUnitCodeMaster.setModifiedBy(longinIDForModifiedBy);
        olderUnitCodeMaster.setModifiedOn(currentDate);

        UnitCodeMasterModule updatedUnitCodeMasterModule = unitCodeMasterRepository.save(olderUnitCodeMaster);
        result.put("success", "Updated Successfully");

        List<UnitCodeMasterModule> unitCodeMasterModuleList = mapBasedOnUnitCodeDescription.containsKey(updatedUnitCodeMasterModule.getUnitDescription()) ? mapBasedOnUnitCodeDescription.get(updatedUnitCodeMasterModule.getUnitDescription()) : new ArrayList<>();
        unitCodeMasterModuleList.add(updatedUnitCodeMasterModule);

        mapBasedOnUnitCodeDescription.put(updatedUnitCodeMasterModule.getUnitDescription(), unitCodeMasterModuleList);
        mapBasedUnitCode.put(updatedUnitCodeMasterModule.getUnitCode(), updatedUnitCodeMasterModule);
    }

    /**
     * Searches for unit code master records based on prefix unit code description or unit code.
     *
     * @param searchPrefix The JSON object containing the search criteria.
     * @param result The map to store the search results.
     * @throws JsonProcessingException If there is an error in JSON processing.
     * @throws JSONException If there is an error in JSON processing.
     */

    public void searchUnitCode(String searchPrefix, Map<String, Object> result) throws JsonProcessingException, JSONException {
        if (CollectionUtils.isEmpty(mapBasedOnUnitCodeDescription))
            mapBasedOnUnitCodeDescription = applicationStartup.getMapBasedOnUnitCodeDescription();
        if (CollectionUtils.isEmpty(mapBasedUnitCode))
            mapBasedUnitCode = applicationStartup.getMapBasedUnitCode();
        JSONObject jsonObject = new JSONObject(searchPrefix);
        String prefixUnitCodeDescription = jsonObject.has("unitCodeDescription") ? (String) jsonObject.get("unitCodeDescription") : "";
//                                           jsonObject.has("unitCodeDescription") ? (String) jsonObject.get("unitCodeDescription") : "";
        long prefixUnitCode = jsonObject.has("unitCode") ? ((Integer) jsonObject.get("unitCode")).longValue() : 0;

        List<UnitCodeMasterModule> searchResult = new ArrayList<>();
        if (prefixUnitCodeDescription.isEmpty() && prefixUnitCode > 0) {

            for (Map.Entry<Long, UnitCodeMasterModule> map : mapBasedUnitCode.entrySet()) {
                Long key = map.getKey();
                if (key.toString().toUpperCase().startsWith(Long.toString(prefixUnitCode).toUpperCase()))
                    searchResult.add(map.getValue());
            }
        } else if (!prefixUnitCodeDescription.isEmpty() && prefixUnitCode == 0) {

            for (Map.Entry<String , List<UnitCodeMasterModule>> map : mapBasedOnUnitCodeDescription.entrySet()) {
                String key = map.getKey();
                if (key.toUpperCase().startsWith(prefixUnitCodeDescription.toUpperCase())) {
                    searchResult.addAll(map.getValue());
                }
            }
        } else {
            for (Map.Entry<String, List<UnitCodeMasterModule>> map : mapBasedOnUnitCodeDescription.entrySet()) {
                String key = map.getKey();
                if(key.toUpperCase().startsWith(prefixUnitCodeDescription.toUpperCase())) {
                    map.getValue().forEach(uCode -> {
                        if (uCode.getUnitCode().toString().toUpperCase().startsWith(String.valueOf(prefixUnitCode).toUpperCase())) {
                            searchResult.add(uCode);
                        }
                    });
                }
            }
        }
        result.put("success", searchResult);
    }

    // Deleting data
   /* public void deleteUnitCode(long unitCode, Map<String, Object> result) {
        if (CollectionUtils.isEmpty(mapBasedOnUnitCodeDescription))
            mapBasedOnUnitCodeDescription = applicationStartup.getMapBasedOnUnitCodeDescription();
        if (CollectionUtils.isEmpty(mapBasedUnitCode))
            mapBasedUnitCode = applicationStartup.getMapBasedUnitCode();

        Optional<UnitCodeMasterModule> optionalUnitCode = unitCodeMasterRepository.findById(unitCode);
        if (optionalUnitCode.isPresent()) {
            UnitCodeMasterModule unitCodeToDelete = optionalUnitCode.get();

            // Remove from mapBasedOnUnitCodeDescription
            List<UnitCodeMasterModule> unitCodeMasterModuleList = mapBasedOnUnitCodeDescription.get(unitCodeToDelete.getUnitDescription());
            if (unitCodeMasterModuleList != null) {
                unitCodeMasterModuleList.removeIf(uCode -> uCode.getUnitCode() == unitCode);
                if (unitCodeMasterModuleList.isEmpty())
                    mapBasedOnUnitCodeDescription.remove(unitCodeToDelete.getUnitDescription());
            }

            // Remove from mapBasedUnitCode
            mapBasedUnitCode.remove(unitCode);

            // Delete from database
            unitCodeMasterRepository.delete(unitCodeToDelete);

            result.put("success", "Deleted Successfully");
        } else {
            result.put("error", "Unit Code not found with id: " + unitCode);
        }
    }*/

    /**
     * Retrieves all unit code descriptions.
     *
     * @param result The map to store the operation result.
     */

   public void getAllUnitCodes(Map<String, Object> result) {
        List<String> unitCodeList = unitCodeMasterRepository.findAllUnitCode();
        result.put("success", unitCodeList);
   }
}



