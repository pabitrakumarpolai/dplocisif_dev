package com.dplocisif.DPLOCISIF.controller;

import com.dplocisif.DPLOCISIF.service.ItemMasterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/item")
public class ItemMasterController {
    @Autowired
    ItemMasterService itemMasterService;

    @RequestMapping(path= "/add-item", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity addItem(@RequestBody String object) throws JSONException, JsonProcessingException, SQLException {
        Map<String, Object> result = new HashMap<>();
        itemMasterService.addItem(object, result);
        if (result.containsKey("error")) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path= "/update-item", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity updateItem(@RequestBody String object) throws JSONException, JsonProcessingException {
        Map<String, Object> result = new HashMap<>();
        itemMasterService.updateItem(object, result);
        if (result.containsKey("error")) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path= "/search-item", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity searchItem(@RequestBody String object) throws JSONException {
        Map<String, Object> result = new HashMap<>();
        itemMasterService.searchItem(object, result);
        if (result.containsKey("error")) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path= "/all-itemcode", method = RequestMethod.GET)
    public ResponseEntity getallItemCode() {
        Map<String, Object> result = new HashMap<>();
        try {
            itemMasterService.getAllItemCode(result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
