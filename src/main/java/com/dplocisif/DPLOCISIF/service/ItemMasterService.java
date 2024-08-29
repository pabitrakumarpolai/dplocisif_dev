package com.dplocisif.DPLOCISIF.service;

import com.dplocisif.DPLOCISIF.exception.LoginIdMissingException;
import com.dplocisif.DPLOCISIF.model.ItemMaster;
import com.dplocisif.DPLOCISIF.repository.ItemRepository;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

/**
 * Service class for managing item masters.
 */

@Service
public class ItemMasterService {
    @Autowired
    ItemRepository itemRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Adds a new item to the database.
     * @param itemMasterData The JSON data representing the item to be added.
     * @param result A Map to store the operation result.
     * @throws JSONException If there is an error in JSON processing.
     * @throws JsonProcessingException If there is an error in JSON processing.
     * @throws SQLException If there is an error in SQL execution.
     */

    public void addItem(String itemMasterData, Map<String, Object> result) throws JSONException, JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JSONObject jsonObject = new JSONObject(itemMasterData);
        String depoCode = jsonObject.has("depoCode") ? jsonObject.getString("depoCode") : "";
        double minStockLevel = jsonObject.has("minStockLevel") ? jsonObject.getLong("minStockLevel") : 0;
        double maxStockLevel = jsonObject.has("maxStockLevel") ? jsonObject.getLong("maxStockLevel") : 0;
        double reorderLevel = jsonObject.has("reorderLevel") ? jsonObject.getLong("reorderLevel") : 0;
        String description = jsonObject.has("itemDescription") ? jsonObject.getString("itemDescription") : "";
        String groupCode = jsonObject.has("groupCode") ? jsonObject.getString("groupCode") : "";

        List<ItemMaster> savedItemMasterList = itemRepository.findByDepoCodeAndItemDescription(depoCode, description.toUpperCase());
        if (!jsonObject.has("loginId")) throw new RuntimeException("Login Id Is Missing");
        long loginId = jsonObject.getLong("loginId");
        if (loginId == 0) throw new LoginIdMissingException("Login Id Is not Present");

        if (!savedItemMasterList.isEmpty()) {
            result.put("error", "This Item Already Have In Same Depo");
        } else if (StringUtils.isBlank(depoCode)) {
            result.put("error", "Please Fill The Mandatory Field");
        } else {
            if (minStockLevel >= maxStockLevel) {
                result.put("error", "Minimum Order Level Should Be Lesser Then Maximum Order Level");
            } else if (reorderLevel < minStockLevel || reorderLevel > maxStockLevel) {
                result.put("error", "Reorder Level Should Be In Between The Minimum And Maximum Order Level");
            } else {
                ItemMaster itemMaster = objectMapper.readValue(itemMasterData, ItemMaster.class);
                itemMaster.setItemDescription(itemMaster.getItemDescription().toUpperCase());

                String generatedItemCode = itemRepository.callGenItemCode(depoCode, groupCode);
                itemMaster.setItemCode(generatedItemCode);

                long currentTimesInMillis = System.currentTimeMillis();
                Date currentDate = new Date(currentTimesInMillis);
                itemMaster.setCreatedBy(loginId);
                itemMaster.setCreatedOn(currentDate);

                ItemMaster savedItem = itemRepository.save(itemMaster);
                result.put("success", savedItem);
            }
        }
    }

    /**
     * Updates an existing item in the database.
     * @param object The JSON data representing the item to be updated.
     * @param result A Map to store the operation result.
     * @throws JsonProcessingException If there is an error in JSON processing.
     * @throws JSONException If there is an error in JSON processing.
     */

    public void updateItem(String object, Map<String, Object> result) throws JsonProcessingException, JSONException {
        JSONObject jsonObject = new JSONObject(object);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ItemMaster itemMaster = objectMapper.readValue(object, ItemMaster.class);

        double minStockLevel = itemMaster.getMinStockLevel();
        double maxStockLevel = itemMaster.getMaxStockLevel();
        double reorderLevel = itemMaster.getReorderLevel();
        Optional<ItemMaster> savedItemMasterByDepoDesc = itemRepository.findByItemDescription(itemMaster.getItemDescription().toUpperCase());
        if (minStockLevel >= maxStockLevel) {
            result.put("error", "Minimum Order Level Should Be Lesser Then Maximum Order Level");
        } else if (reorderLevel < minStockLevel || reorderLevel > maxStockLevel) {
            result.put("error", "Reorder Level Should Be In Between The Minimum And Maximum Order Level");
        } else {
            if (!jsonObject.has("loginId")) throw new RuntimeException("Login Id Is Missing");
            long loginId = jsonObject.getLong("loginId");
            if (loginId == 0) throw new LoginIdMissingException("Login Id Is not Present");

            ItemMaster savedItemMaster = itemRepository.findByDepoCodeAndItemCode(itemMaster.getDepoCode(), itemMaster.getItemCode());
            if (!savedItemMaster.getItemDescription().equals(itemMaster.getItemDescription().toUpperCase()) && savedItemMasterByDepoDesc.isPresent()) {
                result.put("error", "This Item Already Present");
            } else {
                long currentTimesInMillis = System.currentTimeMillis();
                Date currentDate = new Date(currentTimesInMillis);

                itemMaster.setModifiedBy(loginId);
                itemMaster.setModifiedOn(currentDate);

                itemMaster.setItemDescription(itemMaster.getItemDescription().toUpperCase());
                itemMaster.setCreatedBy(savedItemMaster.getCreatedBy());
                itemMaster.setCreatedOn(savedItemMaster.getCreatedOn());

                ItemMaster updatedItemMaster = itemRepository.save(itemMaster);
                result.put("success", updatedItemMaster);
            }
        }
    }

    /**
     * Searches for items based on given criteria.
     * @param object The JSON data representing the search criteria.
     * @param result A Map to store the search result.
     * @throws JSONException If there is an error in JSON processing.
     */

    public void searchItem(String object, Map<String, Object> result) throws JSONException {
        JSONObject jsonObject = new JSONObject(object);
        String depoCode = jsonObject.has("depoCode") ? jsonObject.getString("depoCode") : null;
        String itemCode = jsonObject.has("itemCode") ? jsonObject.getString("itemCode") : null;
        String itemDescription = jsonObject.has("itemDescription") ? jsonObject.getString("itemDescription").toUpperCase() : null;
        int pageNumber = jsonObject.getInt("pageNumber");
        int pageSize = jsonObject.getInt("pageSize");
        String sortBy = jsonObject.has("sortBy") ? jsonObject.getString("sortBy") : "";
        String sortType = jsonObject.has("sortType") ? jsonObject.getString("sortType") : "";

        Pageable pageable = null;
        if (StringUtils.isBlank(sortBy)) {
            pageable = PageRequest.of(pageNumber, pageSize);
        } else {
            if (sortType.equals("ASC")) {
                pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
            } else pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        }
        Page<ItemMaster> itemMasters = itemRepository.searchItems(depoCode, itemCode, itemDescription, pageable);
        result.put("success", itemMasters);
    }

    /**
     * Retrieves all itemcodes.
     * @param result A Map to store the operation result.
     */
    public void getAllItemCode(Map<String, Object> result) {
        List<Long> itemCode = itemRepository.findAllItemCode();
        result.put("success", itemCode);
    }
}
