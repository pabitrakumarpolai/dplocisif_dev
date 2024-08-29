package com.dplocisif.DPLOCISIF.service;

import com.dplocisif.DPLOCISIF.exception.LoginIdMissingException;
import com.dplocisif.DPLOCISIF.model.DepoJobModule;
import com.dplocisif.DPLOCISIF.repository.DepoWiseJobRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * DepoWiseJobService provides methods for adding, updating, and searching for depo-wise jobs.
 */

@Service
public class DepoWiseJobService {
    @Autowired
    DepoWiseJobRepository depoWiseJobRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Adds a new depo-wise job.
     * @param object JSON string containing the job details.
     * @param result A Map to store the operation result.
     * @throws JSONException If there is an error during JSON processing.
     * @throws JsonProcessingException If there is an error during JSON processing.
     */

    public void addDepoWiseJob(String object, Map<String, Object> result) throws JSONException, JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JSONObject jsonObject = new JSONObject(object);
        String depoCode = jsonObject.has("depoCode") ? jsonObject.getString("depoCode") : "";
        long jobCode = jsonObject.has("jobCode") ? jsonObject.getLong("jobCode") : 0;
        String jobDescription = jsonObject.has("jobDescription") ? jsonObject.getString("jobDescription").toUpperCase() : "";
        long longinIDForCreatedBy = jsonObject.has("loginId") ? jsonObject.getLong("loginId") : 0;
        if (longinIDForCreatedBy == 0) throw new LoginIdMissingException("Login id Not Present");

        if (StringUtils.isBlank(depoCode) ) {
            result.put("error", "Select a Depo Code");
        }
        if (jobCode==0 && StringUtils.isBlank(jobDescription)) {
            result.put("error", "Please write valid job code and job description");
        }

        List<DepoJobModule> savedDepoJobList = depoWiseJobRepository.findByDepoCodeAndJobDescription(depoCode, jobDescription.toUpperCase());
        if (!savedDepoJobList.isEmpty()) {
            result.put("error", "This Job Description with the same DepoCode and JobCode is already present!!");
        }else {

            DepoJobModule depoJobModule = objectMapper.readValue(object, DepoJobModule.class);
            depoJobModule.setJobDescription(depoJobModule.getJobDescription().toUpperCase());
            long currentTimesInMillis = System.currentTimeMillis();
            Date currentDate = new Date(currentTimesInMillis);
            depoJobModule.setCreatedBy(longinIDForCreatedBy);
            depoJobModule.setCreatedOn(currentDate);

            depoWiseJobRepository.save(depoJobModule);
            result.put("success", "Job added Successfully");
        }
    }

    /**
     * Updates an existing depo-wise job.
     * @param object JSON string containing the updated job details.
     * @param result A Map to store the operation result.
     * @throws JSONException If there is an error during JSON processing.
     * @throws JsonProcessingException If there is an error during JSON processing.
     */

    public void upadteDepoWiseJob(String object, Map<String, Object> result) throws JSONException, JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JSONObject jsonObject = new JSONObject(object);
        String depoCode = jsonObject.has("depoCode") ? jsonObject.getString("depoCode") : "";
        long jobCode = jsonObject.has("jobCode") ? jsonObject.getLong("jobCode") : 0;
        String jobDescription = jsonObject.has("jobDescription") ? jsonObject.getString("jobDescription") : "";

        if (StringUtils.isBlank(depoCode) ) {
            result.put("error", "Select a Depo Code");
        }
        if (StringUtils.isEmpty(jobDescription)){
            result.put("error","please Add Valid Job Description");
        }

        DepoJobModule depoJobModule = objectMapper.readValue(object, DepoJobModule.class);

        long longinIDForModifiedBy = jsonObject.has("loginId") ? jsonObject.getLong("loginId") : 0;
        if (longinIDForModifiedBy == 0) throw new LoginIdMissingException("Login id Not Present");

        long currentTimesInMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimesInMillis);

        DepoJobModule depoJobModuleList = depoWiseJobRepository.findByDepoCodeAndJobCode(depoCode,jobCode);
        if (depoJobModuleList == null) {
            result.put("error", "Depo Job not found");
            return;
        }
        if (!depoJobModuleList.getJobDescription().equalsIgnoreCase(jobDescription)) {
            depoJobModuleList.setJobDescription(jobDescription.toUpperCase()); // Update jobDescription if it's different
        }
        depoJobModuleList.setModifiedBy(longinIDForModifiedBy);
        depoJobModuleList.setModifiedOn(currentDate);
        depoWiseJobRepository.save(depoJobModuleList);
        result.put("success","Job Description updated Successfully");
    }

    /**
     * Searches for depo-wise jobs based on depo code, job code, and job description prefix.
     * @param object JSON string containing the search criteria.
     * @param result A Map to store the search result.
     * @throws JSONException If there is an error during JSON processing.
     */

    public void searchDepoWiseJob(String object, Map<String, Object> result) throws JSONException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JSONObject jsonObject = new JSONObject(object);
        String depoCode = jsonObject.has("depoCode") ? jsonObject.getString("depoCode") : "";
        Long jobCode = jsonObject.has("jobCode") ? jsonObject.getLong("jobCode") : 0;
        String jobDescription = jsonObject.has("jobDescription") ? jsonObject.getString("jobDescription").toUpperCase() : "";
        if (jobCode.longValue()==0){
            jobCode=null;
        }
        List<DepoJobModule> depoJobModules = depoWiseJobRepository.searchJobs(depoCode, jobCode, jobDescription);
        result.put("success", depoJobModules);
    }

}
