package com.dplocisif.DPLOCISIF.controller;

import com.dplocisif.DPLOCISIF.service.TransactionCodeService;
import org.json.JSONException;
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
@RequestMapping("/trans")
public class TransactionCodeController {
    @Autowired
    TransactionCodeService transactionCodeService;

    @RequestMapping(path = "/get/trans-code", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity getTransactionCodes(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            transactionCodeService.getTransactionCodes(result, object);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            result.put("error", "Some Error in Transaction Code");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
