package com.dplocisif.DPLOCISIF.controller;


import com.dplocisif.DPLOCISIF.service.ClaimRejectionItemServices;
import com.dplocisif.DPLOCISIF.service.EmployeeService;
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
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ClaimRejectionItem") // change the api end point formate it should be small
public class ClaimRejectionItemController {
    @Autowired
    ClaimRejectionItemServices claimRejectionItemServices;

    @RequestMapping(path= "/add-item", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity addClaimRejectionItem(@RequestBody String object) throws JSONException, JsonProcessingException, SQLException, ParseException {
        Map<String, Object> result = new HashMap<>();
        claimRejectionItemServices.addClaimRejItem(object, result);
        if (result.containsKey("error")) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path= "/get-inspection-list", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity getInspectionList(@RequestBody String object) throws JSONException, JsonProcessingException, SQLException {
        Map<String, Object> result = new HashMap<>();
        claimRejectionItemServices.getInspectionList(object, result);
        if (result.containsKey("error")) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
