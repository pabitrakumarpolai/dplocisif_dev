package com.dplocisif.DPLOCISIF.controller;

import com.dplocisif.DPLOCISIF.service.ChallanDetailsHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/challan/header")
public class ChallanDetailsHeaderController {
    @Autowired
    ChallanDetailsHeaderService challanDetailsHeaderService;

    @RequestMapping(path= "/get-by-id", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity getChallanHeaderDetilsByChallanId(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            challanDetailsHeaderService.getChallanDetilsByChallanId(object, result);
            return result.containsKey("error") ? new ResponseEntity<>(result, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(path= "/get/po-list", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity getPoListPr(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            challanDetailsHeaderService.getPoListPr(result, object);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            result.put("Error", "SOme Thing Went Wrong in PoListPr");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
