package com.dplocisif.DPLOCISIF.service;

import com.dplocisif.DPLOCISIF.Enum.CoreConstansts;
import com.dplocisif.DPLOCISIF.common.CommonUtil;
import com.dplocisif.DPLOCISIF.exception.LoginIdMissingException;
import com.dplocisif.DPLOCISIF.filterspecification.InspectionFilterSpecification;
import com.dplocisif.DPLOCISIF.filterspecification.StockAdjFilterSpecification;
import com.dplocisif.DPLOCISIF.model.StockAdjustment;
import com.dplocisif.DPLOCISIF.repository.ProcedureRepository.StockAdjEmpRepository;
import com.dplocisif.DPLOCISIF.repository.ProcedureRepository.StockAdjProcedureRepository;
import com.dplocisif.DPLOCISIF.repository.StockAdjRepository;
import com.dplocisif.DPLOCISIF.repository.view.StockAdjViewRepository;
import com.dplocisif.DPLOCISIF.startupdto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StockAdjustmentService {
    @Autowired
    StockAdjRepository stockAdjRepository;
    @Autowired
    StockAdjEmpRepository stockAdjEmpRepository;
    @Autowired
    StockAdjProcedureRepository stockAdjProcedureRepository;
    @Autowired
    StockAdjViewRepository stockAdjViewRepository;
    @Autowired
    CommonUtil commonUtil;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void getItemByDepoCode(String object, Map<String, Object> result) throws JSONException {
        JSONObject jsonObject = new JSONObject(object);
        String depoCode = jsonObject.has("depoCode") ? jsonObject.getString("depoCode") : "";
        List<StockAdjustmentDTO> stockAdjustmentDTOS = prepareItemDetails(depoCode);
        result.put("success", stockAdjustmentDTOS);
    }

    private List<StockAdjustmentDTO> prepareItemDetails(String depoCode) {
        List<StockAdjustmentDTO> stockAdjustmentDTOS = stockAdjEmpRepository.getAllItemList(depoCode);
        return stockAdjustmentDTOS;
    }

    public void getEmpByLoginId(String object, Map<String, Object> result) throws JSONException {
        JSONObject jsonObject = new JSONObject(object);
        long adjustedBy = jsonObject.has("adjustedBy") ? jsonObject.getLong("adjustedBy") : 0;
        List<StockAdjEmpDTO> stockAdjustmentDTOS = prepareItemDetails(adjustedBy);
        result.put("success", stockAdjustmentDTOS);
    }

    private List<StockAdjEmpDTO> prepareItemDetails(long adjustedBy) {
        List<StockAdjEmpDTO> stockAdjustmentDTOS = stockAdjEmpRepository. getAllEmpList(adjustedBy);
        return stockAdjustmentDTOS;
    }

    public void getAllDepoList(String object,Map<String,Object> result) throws JSONException{
        JSONObject jsonObject = new JSONObject(object);
        String depoCode = jsonObject.has("depoCode")? jsonObject.getString("depoCode") : "";
        List<StockDepoListDTO> stockDepoListDTOS = stockAdjEmpRepository.getAllDepoList(depoCode);
        result.put("success",stockDepoListDTOS);
    }

    public void createStockAdjustment(String object, Map<String, Object> result) throws JSONException, JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JSONObject jsonObject = new JSONObject(object);
        String tranType = jsonObject.has("tranType")? jsonObject.getString("tranType"):"";
        String depoCode = jsonObject.has("depoCode") ? jsonObject.getString("depoCode") : "";
        String itemCode = jsonObject.has("itemCode") ? jsonObject.getString("itemCode") : "";
        long unitCode = jsonObject.has("unitCode") ? jsonObject.getLong("unitCode") : 0;
        String debitCreditFlag = jsonObject.has("debitCreditFlag") ? jsonObject.getString("debitCreditFlag") : "";
        long adjustedQty = jsonObject.has("adjustedQty") ? jsonObject.getLong("adjustedQty") :0;
        String locationBinDesc = jsonObject.has("locationBinDesc") ? jsonObject.getString("locationBinDesc").toUpperCase() : "";
        long binNumber = jsonObject.has("binNumber") ? jsonObject.getLong("binNumber") :0;
        String remarks = jsonObject.has("remarks") ? jsonObject.getString("remarks").toUpperCase() : "";
        long systemLoginId = jsonObject.has("CreatedBy")? jsonObject.getLong("CreatedBy"):0;
        if (systemLoginId == 0) throw new LoginIdMissingException("Login id Not Present");

        Long stockAdjSrlNo = stockAdjRepository.callGenSerialNo(CoreConstansts.STOCK_ADJUSTMENT_TABLE_NAME.getData(),CoreConstansts.STOCK_ADJUSTMENT_COL_NAME.getData());
        StockAdjustment stockAdjustment = objectMapper.readValue(object, StockAdjustment.class);
        stockAdjustment.setStockAdjSrlNo(stockAdjSrlNo);
        stockAdjustment.setRemarks(stockAdjustment.getRemarks().toUpperCase());
        stockAdjustment.setAdjustedBy(systemLoginId);
        stockAdjustment.setCreatedBy(systemLoginId);
        stockAdjustment.setCreatedOn(commonUtil.getCurrentTime());
        stockAdjustment.setAdjustedOn(commonUtil.getCurrentTime());
        stockAdjRepository.save(stockAdjustment);
        stockAdjProcedureRepository.createStockAdjustment(tranType,depoCode,itemCode,debitCreditFlag,adjustedQty,null);
        result.put("success","Stock Adjustment Created");
    }

    public void updateStock(String object, Map<String, Object> result) throws JSONException, JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JSONObject jsonObject = new JSONObject(object);
        String tranType = jsonObject.has("tranType")? jsonObject.getString("tranType"):"";
        String depoCode = jsonObject.has("depoCode") ? jsonObject.getString("depoCode") : "";
        String itemCode = jsonObject.has("itemCode") ? jsonObject.getString("itemCode") : "";
        String debitCreditFlag = jsonObject.has("debitCreditFlag") ? jsonObject.getString("debitCreditFlag") : "";
        long stockAdjSrlNo = jsonObject.has("stockAdjSrlNo") ? jsonObject.getLong("stockAdjSrlNo") :0;
        long adjustedQty = jsonObject.has("adjustedQty") ? jsonObject.getLong("adjustedQty") :0;
        long systemLoginId = jsonObject.has("ModifiedBy")? jsonObject.getLong("ModifiedBy"):0;
        if (systemLoginId == 0) throw new LoginIdMissingException("Login id Not Present");

        StockAdjustment stockAdjustment = objectMapper.readValue(object, StockAdjustment.class);

        Optional<StockAdjustment> optionalStockAdjustment = stockAdjRepository.findById(stockAdjSrlNo);
        if (optionalStockAdjustment.isPresent()){
            StockAdjustment olderStock = optionalStockAdjustment.get();
            if (olderStock.getAdjustedQty() != stockAdjustment.getAdjustedQty()){
                olderStock.setAdjustedQty(stockAdjustment.getAdjustedQty());
                olderStock.setDebitCreditFlag(stockAdjustment.getDebitCreditFlag());
                olderStock.setRemarks(stockAdjustment.getRemarks().toUpperCase());
                olderStock.setModifiedBy(systemLoginId);
                olderStock.setModifiedOn(commonUtil.getCurrentTime());

                stockAdjRepository.save(olderStock);
                stockAdjProcedureRepository.createStockAdjustment(tranType,depoCode,itemCode,debitCreditFlag,adjustedQty,stockAdjSrlNo);
                result.put("success","stock updated Successfully");
            }else {
                result.put("error","Stock is Already updated");
            }

        }else {
            result.put("error","Stock is not found !!");
        }

    }

    public void searchStock(Map<String, Object> result, String object) throws JSONException, JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JSONObject jsonObject = new JSONObject(object);
        StockAdjFilterDTO stockAdjFilterDTO = objectMapper.readValue(object, StockAdjFilterDTO.class);
        int pageNumber = jsonObject.has("pageNo") ? jsonObject.getInt("pageNo") : 0;
        int pageSize = jsonObject.has("pageSize") ? jsonObject.getInt("pageSize") : 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        StockAdjFilterSpecification stockAdjFilterSpecification = new StockAdjFilterSpecification(stockAdjFilterDTO);
        Page<StockAdjViewDTO> stockAdjView = stockAdjViewRepository.findAll(stockAdjFilterSpecification,pageable);
        result.put("success", stockAdjView);

    }

    public void deleteStock(String object, Map<String, Object> result) throws JSONException, JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JSONObject jsonObject = new JSONObject(object);
        String tranType = jsonObject.has("tranType")? jsonObject.getString("tranType"):"";
        String depoCode = jsonObject.has("depoCode") ? jsonObject.getString("depoCode") : "";
        String itemCode = jsonObject.has("itemCode") ? jsonObject.getString("itemCode") : "";
        String debitCreditFlag = jsonObject.has("debitCreditFlag") ? jsonObject.getString("debitCreditFlag") : "";
        long stockAdjSrlNo = jsonObject.has("stockAdjSrlNo") ? jsonObject.getLong("stockAdjSrlNo") :0;
        long adjustedQty = jsonObject.has("adjustedQty") ? jsonObject.getLong("adjustedQty") :0;

//        Optional<StockAdjustment> optionalStockAdjustment = stockAdjRepository.findById(stockAdjSrlNo);

//        if (optionalStockAdjustment.isPresent()) {
//            StockAdjustment stockAdjustment = optionalStockAdjustment.get();
        StockAdjustment stockAdjustment = objectMapper.readValue(object, StockAdjustment.class);
            stockAdjRepository.delete(stockAdjustment);
            stockAdjProcedureRepository.createStockAdjustment(tranType,depoCode,itemCode,debitCreditFlag,adjustedQty,stockAdjSrlNo);
            result.put("success", "Stock deleted successfully");
//        } else {
//            result.put("error", "Stock adjustment not found");
//        }
    }


}
