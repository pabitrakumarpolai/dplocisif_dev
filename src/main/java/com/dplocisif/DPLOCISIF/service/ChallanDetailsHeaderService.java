package com.dplocisif.DPLOCISIF.service;

import com.dplocisif.DPLOCISIF.config.ProcedureConnectionCall;
import com.dplocisif.DPLOCISIF.repository.ChallanDetailsHeaderRepository;
import com.dplocisif.DPLOCISIF.repository.ProcedureRepository.ChallanHeaderRepository;
import com.dplocisif.DPLOCISIF.startupdto.ChallanDetailsHeaderDTO;
import com.dplocisif.DPLOCISIF.startupdto.PoListDTO;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChallanDetailsHeaderService {
    @Autowired
    ChallanHeaderRepository challanHeaderRepository;

    public void getChallanDetilsByChallanId(String object, Map<String, Object> result) throws JSONException {
        JSONObject jsonObject = new JSONObject(object);
        String challanNumber = jsonObject.has("challanNumber") ? jsonObject.getString("challanNumber") : "";
        List<ChallanDetailsHeaderDTO> challanDetailHeaderList = prepareChallanDetailsHeader(challanNumber);
        result.put("success", challanDetailHeaderList);
    }

    private List<ChallanDetailsHeaderDTO> prepareChallanDetailsHeader(String challanNumber) {
        List<ChallanDetailsHeaderDTO> challanDetailsHeaderDTOS = challanHeaderRepository.getAllChallanListInspection(challanNumber);
        return challanDetailsHeaderDTOS;
    }

    public void getPoListPr(Map<String, Object> result, String object) throws JSONException {
        JSONObject jsonObject = new JSONObject(object);
        String depoCode = jsonObject.has("depoCode") ? jsonObject.getString("depoCode") : null;
        List<PoListDTO> poListDTOS = challanHeaderRepository.getPoListPr(depoCode);
        result.put("success", poListDTOS);
    }
}
