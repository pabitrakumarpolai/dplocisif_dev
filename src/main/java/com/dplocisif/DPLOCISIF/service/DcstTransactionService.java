package com.dplocisif.DPLOCISIF.service;

import com.dplocisif.DPLOCISIF.dplApplicationStartup.ApplicationStartup;
import com.dplocisif.DPLOCISIF.exception.LoginIdMissingException;
import com.dplocisif.DPLOCISIF.model.DcstTransaction;
import com.dplocisif.DPLOCISIF.model.DsctDepo;
import com.dplocisif.DPLOCISIF.repository.DcstTransactionRepository;
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
 * DcstTransactionService provides methods for adding, updating, and searching DCST transactions.
 */

@Service
public class DcstTransactionService {

    @Autowired
    DcstTransactionRepository dcstTransactionRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Adds a new DCST transaction.
     * @param object JSON string containing the transaction details.
     * @param result A Map to store the operation result.
     * @throws JSONException If there is an error during JSON processing.
     * @throws JsonProcessingException If there is an error during JSON processing.
     */

    public void addDsctTransaction(String object, Map<String, Object> result) throws JSONException, JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JSONObject jsonObject = new JSONObject(object);
        DcstTransaction dcstTransaction = objectMapper.readValue(object, DcstTransaction.class);
        dcstTransaction.setTransDescription(dcstTransaction.getTransDescription().toUpperCase());

        long longinIDForCreatedBy = jsonObject.has("loginId") ? jsonObject.getLong("loginId") : 0;
        if (longinIDForCreatedBy == 0) throw new LoginIdMissingException("Login id Not Present");

        List<DcstTransaction> transactionBasedDescription = dcstTransactionRepository.findAllTransactionCodeAndTransactionDescription(dcstTransaction.getTransactionCode(), dcstTransaction.getTransDescription());
        if (transactionBasedDescription.isEmpty()){
            long currentTimesInMillis = System.currentTimeMillis();
            Date currentDate = new Date(currentTimesInMillis);
            dcstTransaction.setCreatedBy(longinIDForCreatedBy);
            dcstTransaction.setCreatedOn(currentDate);
            dcstTransaction.setTransDescription(dcstTransaction.getTransDescription().toUpperCase());
            DcstTransaction savedDcstTransaction = dcstTransactionRepository.save(dcstTransaction);
            result.put("success", savedDcstTransaction);
        } else {
            result.put("error", "Data Injected By You Duplicate!!");
        }

    }

    /**
     * Updates an existing DCST transaction.
     * @param object JSON string containing the updated transaction details.
     * @param result A Map to store the operation result.
     * @throws JsonProcessingException If there is an error during JSON processing.
     * @throws JSONException If there is an error during JSON processing.
     */

    public void updateDcstTransaction(String object, Map<String, Object> result) throws JsonProcessingException, JSONException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JSONObject jsonObject = new JSONObject(object);
        DcstTransaction dcstTransaction = objectMapper.readValue(object, DcstTransaction.class);
        dcstTransaction.setTransDescription(dcstTransaction.getTransDescription().toUpperCase());

        long longinIDForModifiedBy = jsonObject.has("loginId") ? jsonObject.getLong("loginId") : 0;
        if (longinIDForModifiedBy == 0) throw new LoginIdMissingException("Login id Not Present");

        long currentTimesInMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimesInMillis);

        Optional<DcstTransaction> optionalDcstTransaction = dcstTransactionRepository.findByTransactionCode(dcstTransaction.getTransactionCode());
        if (!optionalDcstTransaction.isEmpty()){
            DcstTransaction olderDcstTransaction = optionalDcstTransaction.get();
            olderDcstTransaction.setTransDescription(dcstTransaction.getTransDescription());
            olderDcstTransaction.setModifiedBy(longinIDForModifiedBy);
            olderDcstTransaction.setModifiedOn(currentDate);

            DcstTransaction updatedDcstTransaction = dcstTransactionRepository.save(olderDcstTransaction);
            result.put("success", updatedDcstTransaction);
        }else {
            result.put("error","Transaction Code is Wrong!!");
        }

    }

    /**
     * Searches for DCST transactions based on transaction code and description prefix.
     * @param searchPrefix JSON string containing the search prefix.
     * @param result A Map to store the search result.
     * @throws JsonProcessingException If there is an error during JSON processing.
     * @throws JSONException If there is an error during JSON processing.
     */


    public void searchDcstTransaction(String searchPrefix, Map<String, Object> result) throws JsonProcessingException, JSONException {
        JSONObject jsonObject = new JSONObject(searchPrefix);
        String prefixTransDescription = jsonObject.has("Transdescription") ? ((String) jsonObject.get("Transdescription")).toUpperCase() : "";
        Long prefixTransactionCode = jsonObject.has("Transcode") ? (jsonObject.getLong("Transcode")) : 0;

        if (prefixTransactionCode==0){
            prefixTransactionCode=null;
        }
        List<DcstTransaction> searchResult = dcstTransactionRepository.findByTransaction(prefixTransactionCode,prefixTransDescription);

        result.put("success", searchResult);

    }

}
