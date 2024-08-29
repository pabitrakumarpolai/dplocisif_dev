package com.dplocisif.DPLOCISIF.service;

import com.dplocisif.DPLOCISIF.Enum.CoreConstansts;
import com.dplocisif.DPLOCISIF.common.CommonUtil;
import com.dplocisif.DPLOCISIF.exception.LoginIdMissingException;
import com.dplocisif.DPLOCISIF.filterspecification.InspectionFilterSpecification;
import com.dplocisif.DPLOCISIF.model.ItemInspection;
import com.dplocisif.DPLOCISIF.repository.ItemInspectionRepository;
import com.dplocisif.DPLOCISIF.repository.ProcedureRepository.ChallanChildDetailsRepository;
import com.dplocisif.DPLOCISIF.repository.ProcedureRepository.ChallanHeaderRepository;
import com.dplocisif.DPLOCISIF.repository.view.ItemInspectionViewRepository;
import com.dplocisif.DPLOCISIF.startupdto.ChallanChildDetailsDTO;
import com.dplocisif.DPLOCISIF.startupdto.ChallanDetailsHeaderDTO;
import com.dplocisif.DPLOCISIF.startupdto.InspectionViewDTO;
import com.dplocisif.DPLOCISIF.startupdto.ItemInspectionFilterDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Service class for managing item inspections.
 */

@Service
public class ItemInspectionService {
    @Autowired
    ItemInspectionRepository itemInspectionRepository;
    @Autowired
    CommonUtil commonUtil;
    @Autowired
    ChallanHeaderRepository challanHeaderRepository;
    @Autowired
    ChallanChildDetailsRepository challanChildDetailsRepository;
    @Autowired
    ItemInspectionViewRepository itemInspectionViewRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Adds a new item inspection.
     * @param result A Map to store the operation result.
     * @param object The JSON object containing item inspection information.
     * @throws JSONException If there is an error in JSON processing.
     * @throws JsonProcessingException If there is an error in JSON processing.
     * @throws ParseException If there is an error in parsing the date.
     */

    public void addItemInspection(Map<String, Object> result, String object) throws JSONException, JsonProcessingException, ParseException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JSONObject jsonObject = new JSONObject(object);
        long challanQuantityNumber = jsonObject.has("challanQtyInNumber") ? jsonObject.getLong("challanQtyInNumber") : 0;
        long quantityAccepted = jsonObject.has("qtyAccepted") ? jsonObject.getLong("qtyAccepted") : 0;
        long quantityRejected = jsonObject.has("qtyRejected") ? jsonObject.getLong("qtyRejected") : 0;
        long loginId = jsonObject.has("loginId") ? jsonObject.getLong("loginId") : 0;
        if (loginId == 0) throw new LoginIdMissingException("Login id Missing");

        Date date = jsonObject.has("inspectionDate") ? new SimpleDateFormat("dd/MM/yyyy").parse(jsonObject.getString("inspectionDate")) : null;
        if (quantityAccepted + quantityRejected <= challanQuantityNumber && challanQuantityNumber > 0) {
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            String inspectionNumber = itemInspectionRepository.callGenPrimaryKey(CoreConstansts.ITEM_INSPECTION_PREFIX.getData(), CoreConstansts.ITEM_INSPECTION_TABLE_NAME.getData(),
                    CoreConstansts.ITEM_INSPECTION_COL_NAME.getData(), CoreConstansts.ITEM_INSPECTION_LENGTH.getValue(), sqlDate);
            ItemInspection itemInspection = objectMapper.readValue(object, ItemInspection.class);
            itemInspection.setInspectionNo(inspectionNumber);
            itemInspection.setCreatedBy(loginId);
            itemInspection.setCreatedOn(commonUtil.getCurrentTime());
            itemInspectionRepository.save(itemInspection);
            result.put("success", "Item inspection Is Successfully Saved And your Inspection Number is ".concat(inspectionNumber));
        } else {
            result.put("error", "Addition of Quantity Accepted and Quantity Rejected should be lesser then or equal Challan Quantity Number");
        }
    }

    public void updateItemInspection(Map<String, Object> result, String object) throws JSONException, JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JSONObject jsonObject = new JSONObject(object);
        String challanNumber = jsonObject.has("challanNo") ? jsonObject.getString("challanNo") : "";
        Long challanSerialNumber = jsonObject.has("challanSerialNumber") ? jsonObject.getLong("challanSerialNumber") : null;
        if (StringUtils.isBlank(challanNumber) || Objects.isNull(challanSerialNumber)) {
            result.put("error", "Challan Number Or Challan Serial Number Field Should Be Empty");
        } else {
            long quantityAccepted = jsonObject.has("qtyAccepted") ? jsonObject.getLong("qtyAccepted") : 0;
            long quantityRejected = jsonObject.has("qtyRejected") ? jsonObject.getLong("qtyRejected") : 0;
            long loginId = jsonObject.has("loginId") ? jsonObject.getLong("loginId") : 0;
            if (loginId == 0) throw new LoginIdMissingException("Login Id Is not Present");

            List<ChallanDetailsHeaderDTO> challanDetailsHeaderDTOS = challanHeaderRepository.getAllChallanListInspection(challanNumber);
            if (challanDetailsHeaderDTOS.isEmpty()) throw new RuntimeException("Something Went Wrong By the Time Of Fetching the Challan Detils");

            List<ChallanChildDetailsDTO> challanChildDetailsDTOList = challanChildDetailsRepository.getAllChallanListInspectionPr(challanNumber, challanSerialNumber);
            if (challanChildDetailsDTOList.isEmpty()) throw new RuntimeException("Something Went Wrong in Challan Pr");
            long challanQuantityNumber = challanChildDetailsDTOList.get(0).getChallanQtyInNumber();

            if (quantityAccepted + quantityRejected <= challanQuantityNumber && challanQuantityNumber > 0) {

                ItemInspection itemInspection = objectMapper.readValue(object, ItemInspection.class);
                Optional<ItemInspection> optionalSavedItemInspection = itemInspectionRepository.findById(itemInspection.getInspectionNo());

                ItemInspection savedItemInspection = optionalSavedItemInspection.get();
                itemInspection.setCreatedOn(savedItemInspection.getCreatedOn());
                itemInspection.setCreatedBy(savedItemInspection.getCreatedBy());
                itemInspection.setModifiedBy(loginId);
                itemInspection.setModifiedOn(commonUtil.getCurrentTime());

                ItemInspection updatedItemInspection = itemInspectionRepository.save(itemInspection);

                List<Object> resultList = List.of(updatedItemInspection, challanDetailsHeaderDTOS, challanChildDetailsDTOList);
                result.put("success", resultList);
            } else {
                result.put("error", "Addition of Quantity Accepted and Quantity Rejected should be lesser then or equal Challan Quantity Number");
            }
        }
    }

    public void searchItemInspection(Map<String, Object> result, String object) throws JSONException, JsonProcessingException {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JSONObject jsonObject = new JSONObject(object);
            ItemInspectionFilterDTO itemInspectionFilterDTO = objectMapper.readValue(object, ItemInspectionFilterDTO.class);
        int pageNumber = jsonObject.has("pageNo") ? jsonObject.getInt("pageNo") : 0;
        int pageSize = jsonObject.has("pageSize") ? jsonObject.getInt("pageSize") : 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        InspectionFilterSpecification inspectionFilterSpecification = new InspectionFilterSpecification(itemInspectionFilterDTO);
        Page<InspectionViewDTO> inspectionViewDTOPage = itemInspectionViewRepository.findAll(inspectionFilterSpecification, pageable);
        result.put("success", inspectionViewDTOPage);
    }
}
