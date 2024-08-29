package com.dplocisif.DPLOCISIF.service;

import com.dplocisif.DPLOCISIF.DAO.ClaimRejectedItemDAO;
import com.dplocisif.DPLOCISIF.common.CommonUtil;
import com.dplocisif.DPLOCISIF.exception.LoginIdMissingException;
import com.dplocisif.DPLOCISIF.model.ClaimRejectionItem;
import com.dplocisif.DPLOCISIF.model.DcstTransaction;
import com.dplocisif.DPLOCISIF.repository.ClaimRejectionItemRepository;
import com.dplocisif.DPLOCISIF.repository.ProcedureRepository.ClaimRejectedProcedureRepository;
import com.dplocisif.DPLOCISIF.startupdto.ClaimRejectedItemKeyGenDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class ClaimRejectionItemServices {
    @Autowired
    ClaimRejectionItemRepository claimRejectionItemRepository;
    @Autowired
    ClaimRejectedItemDAO claimRejectedItemDAO;
    @Autowired
    ClaimRejectedProcedureRepository claimRejectedProcedureRepository;

    private  ObjectMapper objectMapper = new ObjectMapper();

    public void addClaimRejItem(String object, Map<String, Object> result) throws JSONException, JsonProcessingException, SQLException, ParseException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JSONObject jsonObject = new JSONObject(object);
        long loginId = jsonObject.has("loginId") ? jsonObject.getLong("loginId") : 0;
        if (loginId == 0) throw new LoginIdMissingException("Login Id Not Present");
        Date claimDate = jsonObject.has("claimDate") ? new SimpleDateFormat("dd/MM/yyyy").parse(jsonObject.getString("claimDate")) : new Date(System.currentTimeMillis());
       String claimNo="";
       claimNo=claimRejectedItemDAO.getCliamCode("CLM",claimDate);
       ClaimRejectionItem claimRejectionItem = objectMapper.readValue(object, ClaimRejectionItem.class);
        long currentTimesInMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimesInMillis);
        claimRejectionItem.setCreatedBy(loginId);
        claimRejectionItem.setCreatedOn(currentDate);
        claimRejectionItem.setClaimNo(claimNo);
        claimRejectionItemRepository.save(claimRejectionItem);
        result.put("success", "Data Save Successfully");
    }


    public void getInspectionList(String object, Map<String, Object> result) throws JSONException {

        JSONObject jsonObject = new JSONObject(object);
        String inspectionNo = jsonObject.has("inspectionNo") ? jsonObject.getString("inspectionNo") : "";
        List<ClaimRejectedItemKeyGenDTO> claimRejectedItemKeyGenDTOList=null;
        claimRejectedItemKeyGenDTOList=claimRejectedProcedureRepository.getInspectionList(inspectionNo);
        result.put("success", claimRejectedItemKeyGenDTOList);
    }
}
