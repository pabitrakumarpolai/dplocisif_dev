package com.dplocisif.DPLOCISIF.controller;

import com.dplocisif.DPLOCISIF.service.ChallanDetailsChildService;
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
@RequestMapping("/child/challan")
public class ChallanDetailsChildController {
    @Autowired
    ChallanDetailsChildService challanDetailsChildService;

    @RequestMapping(path= "/get/-by-id", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity getChildDetailsListPr(@RequestBody String object) throws JSONException {
        Map<String, Object> result = new HashMap<>();
        challanDetailsChildService.getChildDetailsListPr(result, object);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path= "/get/ord-srl-no", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity getOrderedSrlNo(@RequestBody String object) throws JSONException {
        Map<String, Object> result = new HashMap<>();
        challanDetailsChildService.getOrderedSrlNo(result, object);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path= "/get/job-list", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity getJobListPr(@RequestBody String object) throws JSONException {
        Map<String, Object> result = new HashMap<>();
        challanDetailsChildService.getJobListPr(result, object);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
