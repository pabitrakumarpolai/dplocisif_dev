package com.dplocisif.DPLOCISIF.service;

import com.dplocisif.DPLOCISIF.repository.ProcedureRepository.ChallanChildDetailsRepository;
import com.dplocisif.DPLOCISIF.startupdto.ChallanChildDetailsDTO;
import com.dplocisif.DPLOCISIF.startupdto.JobDTO;
import com.dplocisif.DPLOCISIF.startupdto.PoItemListPrDTO;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChallanDetailsChildService {
    @Autowired
    ChallanChildDetailsRepository challanChildDetailsRepository;

    public void getChildDetailsListPr(Map<String, Object> result, String object) throws JSONException {
        JSONObject jsonObject = new JSONObject(object);

        String challanNumber = jsonObject.has("challanNumber") ? jsonObject.getString("challanNumber") : "";
        Long challanPrNumber = jsonObject.has("challanPrNumber") && jsonObject.getLong("challanPrNumber") != 0 ? jsonObject.getLong("challanPrNumber") : null;

        List<ChallanChildDetailsDTO> challanDetailsChildServices = challanChildDetailsRepository.getAllChallanListInspectionPr(challanNumber, challanPrNumber);
        result.put("success", challanDetailsChildServices);
    }

    public void getOrderedSrlNo(Map<String, Object> result, String object) throws JSONException {
        JSONObject jsonObject = new JSONObject(object);
        String poNumber = jsonObject.has("poNumber") ? jsonObject.getString("poNumber") : "";
        Long ordItmsrlNo = jsonObject.has("ordItmsrlNo") ? jsonObject.getLong("ordItmsrlNo") : null;

        List<PoItemListPrDTO> poItemListPrDTO = challanChildDetailsRepository.getOrderedSrlNo(poNumber, ordItmsrlNo);
        result.put("success", poItemListPrDTO);
    }

    public void getJobListPr(Map<String, Object> result, String object) throws JSONException {
        JSONObject jsonObject = new JSONObject(object);
        String depoCode = jsonObject.has("depoCode") ? jsonObject.getString("depoCode") : "";
        Long jobCode = jsonObject.has("jobCode") ? jsonObject.getLong("jobCode") : null;

        List<JobDTO> jobListDto = challanChildDetailsRepository.getJobListPr(depoCode, jobCode);
        result.put("success", jobListDto);
    }
}
