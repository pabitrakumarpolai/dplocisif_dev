package com.dplocisif.DPLOCISIF.service;

import com.dplocisif.DPLOCISIF.exception.LoginIdMissingException;
import com.dplocisif.DPLOCISIF.model.IndentInstructionInfo;
import com.dplocisif.DPLOCISIF.repository.IndentInstructionInfoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class IndentInstructionInfoService {

    @Autowired
    IndentInstructionInfoRepository indentInstructionInfoRepository;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    // Adding Data
    public void addIndentInstructInfo(String object, Map<String, Object> result) throws JSONException, JsonProcessingException {
        JSONObject jsonObject = new JSONObject(object);

        // Validate and extract loginId
        long loginIdForCreatedBy = jsonObject.has("loginId") ? jsonObject.getLong("loginId") : 0;
        if (loginIdForCreatedBy == 0) throw new LoginIdMissingException("Login id Not Present");

        // Validate and extract isActive
        String isActiveString = null;
        if (jsonObject.has("isActive")) {
            isActiveString = jsonObject.getString("isActive").toUpperCase();
            if (!isActiveString.equals("Y") && !isActiveString.equals("N")) {
                throw new RuntimeException("Invalid value for isActive field. Only 'Y' or 'N' are allowed.");
            }
        }

        IndentInstructionInfo indentInstructionInfo = objectMapper.readValue(object, IndentInstructionInfo.class);

        // Validate srlNo for two digits limit
        if (indentInstructionInfo.getSrlNo() != null && (indentInstructionInfo.getSrlNo() < 0 || indentInstructionInfo.getSrlNo() > 99)) {
            throw new RuntimeException("Invalid value for srlNo. It should be a two-digit number.");
        }

        if (indentInstructionInfo.getInstructDesc() != null) {
            indentInstructionInfo.setInstructDesc(indentInstructionInfo.getInstructDesc().toUpperCase());
        }

        long currentTimeInMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeInMillis);
        indentInstructionInfo.setCreatedBy(loginIdForCreatedBy);
        indentInstructionInfo.setCreatedOn(currentDate);

        if (isActiveString != null) {
            indentInstructionInfo.setIsActive(isActiveString);
        } else {
            indentInstructionInfo.setIsActive("N"); // or any default value you prefer
        }

        indentInstructionInfoRepository.save(indentInstructionInfo);

        result.put("success", "Indent Instruction Info added successfully");
    }



    // Updating Data
    public void updateIndentInstructInfo(String object, Map<String, Object> result) throws JsonProcessingException, JSONException {
        JSONObject jsonObject = new JSONObject(object);

        // Validate and extract loginId
        long logInForModifiedBy = jsonObject.has("loginId") ? jsonObject.getLong("loginId") : 0;
        if (logInForModifiedBy == 0) throw new LoginIdMissingException("Login id Not Present");

        // Validate and extract isActive
        if (!jsonObject.has("isActive")) {
            throw new RuntimeException("isActive field Not Present");
        }
        String isActiveString = jsonObject.getString("isActive").toUpperCase();
        if (!isActiveString.equals("Y") && !isActiveString.equals("N")) {
            throw new RuntimeException("Invalid value for isActive field. Only 'Y' or 'N' are allowed.");
        }

        // Convert JSON to IndentInstructionInfo object
        IndentInstructionInfo indentInstructionInfo = objectMapper.readValue(object, IndentInstructionInfo.class);

        // Find the existing IndentInstructionInfo object by primary key (instSrlNo)
        Optional<IndentInstructionInfo> optionalIndentInstructionInfo = indentInstructionInfoRepository.findById(indentInstructionInfo.getInstSrlNo());
        if (optionalIndentInstructionInfo.isPresent()) {
            // Get the existing object
            IndentInstructionInfo olderIndentInstructInfo = optionalIndentInstructionInfo.get();

            // Track if any field was modified
            boolean isModified = false;

            // Update the instructDesc if it has changed
            if (indentInstructionInfo.getInstructDesc() != null && !indentInstructionInfo.getInstructDesc().equalsIgnoreCase(olderIndentInstructInfo.getInstructDesc())) {
                olderIndentInstructInfo.setInstructDesc(indentInstructionInfo.getInstructDesc().toUpperCase());
                isModified = true;
            }

            // Update the isActive if it has changed
            if (!isActiveString.equals(olderIndentInstructInfo.getIsActive())) {
                olderIndentInstructInfo.setIsActive(isActiveString);
                isModified = true;
            }

            // Update the modifiedBy field
            olderIndentInstructInfo.setModifiedBy(logInForModifiedBy);

            // Update the modifiedOn field only if there was a modification
            if (isModified) {
                olderIndentInstructInfo.setModifiedOn(new Date(System.currentTimeMillis()));
            }

            // Save the updated object
            IndentInstructionInfo updatedIndentInstructInfo = indentInstructionInfoRepository.save(olderIndentInstructInfo);
            result.put("success", updatedIndentInstructInfo);
        } else {
            result.put("error", "Indent instruction info is Wrong!!");
        }
    }


    //Searching Data
   public void searchIndentInstructInfo(String searchPrefix, Map<String, Object> result) throws JsonProcessingException, JSONException {
       JSONObject jsonObject = new JSONObject(searchPrefix);
       String prefixIndentDesc = jsonObject.has("IndentDesc") ? ((String) jsonObject.get("IndentDesc")).toUpperCase() : "";
       Long prefixSrlNo = jsonObject.has("SrlNo") ? (jsonObject.getLong("SrlNo")) : 0;

       if (prefixSrlNo == 0) {
           prefixSrlNo = null;
       }
       List<IndentInstructionInfo> searchResult = indentInstructionInfoRepository.findByIndentInstruct(prefixSrlNo, prefixIndentDesc);
       result.put("success", searchResult);
   }
}