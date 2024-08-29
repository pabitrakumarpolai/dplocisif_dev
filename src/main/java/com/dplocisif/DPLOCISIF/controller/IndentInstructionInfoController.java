package com.dplocisif.DPLOCISIF.controller;

import com.dplocisif.DPLOCISIF.service.IndentInstructionInfoService;
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
@RequestMapping(path = "/indent-inst")
public class IndentInstructionInfoController {
    @Autowired
    IndentInstructionInfoService indentInstructionInfoService;

    //End point for adding
    @RequestMapping(path = "/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity addIndentInstructionInfo(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            indentInstructionInfoService.addIndentInstructInfo(object, result);
            if (result.containsKey("error")) {
                return new ResponseEntity(result.get("error"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    //End point for Updating
    @RequestMapping(path = "/update", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity updateIndentInstructionInfo(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            indentInstructionInfoService.updateIndentInstructInfo(object, result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

    }

    //End point for Searching
   @RequestMapping(path = "/search", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity searchIndentInstructionInfo(@RequestBody String searchPrefix) {
        Map<String, Object> result = new HashMap<>();
        try {
            indentInstructionInfoService.searchIndentInstructInfo(searchPrefix, result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }


    }
}


