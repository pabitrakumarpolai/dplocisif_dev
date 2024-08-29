package com.dplocisif.DPLOCISIF.controller;

import com.dplocisif.DPLOCISIF.service.IndentApproverService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Indentapprove")
public class IndentApproverController {
    @Autowired
    IndentApproverService indentApproverService;

    @RequestMapping(path= "/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity indentApprover(@RequestBody String object) throws IOException, ParseException {
        Map<String,Object> result = new HashMap<>();
        try {
            indentApproverService.indentApproverList(object, result);
            HttpStatus status = result.containsKey("error") ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
            return new ResponseEntity<>(result.containsKey("error") ? result.get("error") : result, status);
        } catch (Exception exception) {
             result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path= "/update", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity updateIndentApprover(@RequestBody String object) throws IOException, ParseException {
        Map<String,Object> result = new HashMap<>();
        try {
            indentApproverService.updateIndentApproverList(object, result);
            HttpStatus status = result.containsKey("error") ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
            return new ResponseEntity<>(result.containsKey("error") ? result.get("error") : result, status);
        } catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path= "/search", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity searchIndentApprover(@RequestBody String object) throws IOException, ParseException {
        Map<String,Object> result = new HashMap<>();
        try {
            indentApproverService.searchIndentApproverList(object, result);
            HttpStatus status = result.containsKey("error") ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
            return new ResponseEntity<>(result.containsKey("error") ? result.get("error") : result, status);
        } catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

}
