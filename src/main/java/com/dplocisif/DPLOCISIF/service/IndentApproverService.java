package com.dplocisif.DPLOCISIF.service;

import com.dplocisif.DPLOCISIF.exception.LoginIdMissingException;
import com.dplocisif.DPLOCISIF.model.IndentApprover;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.dplocisif.DPLOCISIF.repository.IndentApproverRepository;
import com.dplocisif.DPLOCISIF.startupdto.UserLoginNameDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class IndentApproverService {
    @Autowired
    IndentApproverRepository indentApproverRepository;
    @Autowired
    UserLoginService userLoginService;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private ObjectMapper objectMapper = new ObjectMapper();

    public void indentApproverList(String object, Map<String, Object> result) throws JSONException, JsonProcessingException, ParseException, SQLException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JSONObject jsonObject = new JSONObject(object);
        String documentType = jsonObject.has("documentType") ? jsonObject.getString("documentType").toUpperCase() : "";
        long department = jsonObject.has("department") ? jsonObject.getLong("department") : 0;
        long systemLoginId = jsonObject.has("CREATED_BY") ? jsonObject.getLong("CREATED_BY") : 0;
        String fromDateString = jsonObject.has("fromDate") ? jsonObject.getString("fromDate") : null;
        String toDate = jsonObject.has("toDate") ? jsonObject.getString("toDate") : null;
        long loginId = jsonObject.has("loginId") ? jsonObject.getLong("loginId") : 0;

        if (loginId == 0) throw new LoginIdMissingException("Login Id Is not Present");
        List<UserLoginNameDTO> userLoginModules = userLoginService.getAllUserLogin(result,object);
        String ngs = userLoginModules.stream()
                .filter(user -> user.getLoginId() == loginId)
                .map(UserLoginNameDTO::getNgs)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("NGS not found for the provided loginId"));

        if (documentType.isBlank()) {
            result.put("error", "Document Type Should Not Be Blank");
        }
        if (department == 0) {
            result.put("error", "select Atlest One Department");
        }

        IndentApprover indentApprover = objectMapper.readValue(object, IndentApprover.class);
        indentApprover.setDocumentType(indentApprover.getDocumentType().toUpperCase());
        indentApprover.setNgs(ngs);

        long currentTimesInMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimesInMillis);
        indentApprover.setCreatedBy(systemLoginId);
        indentApprover.setCreatedOn(currentDate);

        Date fromDate = null;
        if (fromDateString != null && !fromDateString.isEmpty()) {
            fromDate = dateFormat.parse(fromDateString);
            indentApprover.setFromDate(fromDate);
        }

        Date toDates = null;
        if (toDate != null && !toDate.isEmpty()) {
            toDates = dateFormat.parse(toDate);
            indentApprover.setToDate(toDates);
            if (toDates.before(fromDate)) {
                result.put("error", "toDate must be after fromDate");
                return;
            }
        }

        indentApproverRepository.save(indentApprover);
        result.put("success", "Indent Approved Successfully");
    }


    public void updateIndentApproverList(String object, Map<String, Object> result) throws JSONException, JsonProcessingException, ParseException {
        JSONObject jsonObject = new JSONObject(object);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String documentType = jsonObject.has("documentType") ? jsonObject.getString("documentType").toUpperCase() : "";
        long department = jsonObject.has("department") ? jsonObject.getLong("department") : 0;
        long systemLoginId = jsonObject.has("MODIFIED_BY") ? jsonObject.getLong("MODIFIED_BY") : 0;
        String fromDateString = jsonObject.has("fromDate") ? jsonObject.getString("fromDate") : null;
        String toDate = jsonObject.has("toDate") ? jsonObject.getString("toDate") : null;
//        long loginId = jsonObject.has("loginId") ? jsonObject.getLong("loginId") : 0;
//
//        if (loginId == 0) throw new RuntimeException("Login Id Is not Present");
//        List<UserLoginNameDTO> userLoginModules = userLoginService.getAllUserLogin(result,object);
//        String ngs = userLoginModules.stream()
//                .filter(user -> user.getLoginId() == loginId)
//                .map(UserLoginNameDTO::getNgs)
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("NGS not found for the provided loginId"));
//        IndentApprover indentApprover = objectMapper.readValue(object, IndentApprover.class);
        if (systemLoginId == 0) throw new LoginIdMissingException("Login Id Is not Present");

        List<IndentApprover> indentApproverlist =indentApproverRepository.findByDocumentTypeAndDepartment(documentType.toUpperCase(),department);
        if (!indentApproverlist.isEmpty()){
            IndentApprover olderIndentApprover = indentApproverlist.get(0);
            Date fromDate = null;
            if (fromDateString != null && !fromDateString.isEmpty()) {
                fromDate = dateFormat.parse(fromDateString);
                olderIndentApprover.setFromDate(fromDate);
            }

            Date toDates = null;
            if (toDate != null && !toDate.isEmpty()) {
                toDates = dateFormat.parse(toDate);
                olderIndentApprover.setToDate(toDates);
                if (toDates.before(fromDate)) {
                    result.put("error", "toDate must be after fromDate");
                    return;
                }
            }
//            olderIndentApprover.setLoginId(loginId);
//            olderIndentApprover.setNgs(ngs);
            olderIndentApprover.setModifiedBy(systemLoginId);
            olderIndentApprover.setModifiedOn(new Date(System.currentTimeMillis()));

            indentApproverRepository.save(olderIndentApprover);
            result.put("success","Indent updated successfully");
        }else {
            result.put("error","No matching indent approver found");
        }

    }

    public void searchIndentApproverList(String object, Map<String, Object> result) throws JSONException {
        JSONObject jsonObject = new JSONObject(object);
        String documentType = jsonObject.has("documentType") ? jsonObject.getString("documentType").toUpperCase() : "";
        Long department = jsonObject.has("department") ? jsonObject.getLong("department") : 0;
        if (department==0){
            department=null;
        }
        List<IndentApprover> searchIndent = indentApproverRepository.findByIndent(documentType,department);
        result.put("success",searchIndent);
    }
}