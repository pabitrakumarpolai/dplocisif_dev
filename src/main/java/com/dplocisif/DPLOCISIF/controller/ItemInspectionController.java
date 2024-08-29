package com.dplocisif.DPLOCISIF.controller;

import com.dplocisif.DPLOCISIF.service.ItemInspectionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/item/inspection")
public class ItemInspectionController {
    @Autowired
    ItemInspectionService itemInspectionService;

    @RequestMapping(path= "/add-item", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity addItemInspection(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            itemInspectionService.addItemInspection(result, object);
            return result.containsKey("error") ? new ResponseEntity<>(result, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path= "/update-item", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity updateItemInspection(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            itemInspectionService.updateItemInspection(result, object);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("error", "Error Occured In Update Item Inspection");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path= "/search-item", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity searchItemInspection(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            itemInspectionService.searchItemInspection(result, object);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("error", "Error Occured In Search Item Inspection");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
