package com.dplocisif.DPLOCISIF.controller;

import com.dplocisif.DPLOCISIF.service.DsctDepoService;
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
@RequestMapping("/dest")
public class DsctDepoContoller {
    @Autowired
    DsctDepoService depoService;
    @RequestMapping(path= "/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity addDepo(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            depoService.addDepo(object, result);
            return result.containsKey("error") ? new ResponseEntity<>(result, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path= "/update", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity updateDepo(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            depoService.updateDepo(object, result);
            return result.containsKey("error") ? new ResponseEntity<>(result, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path= "/search", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity searchByDeptCodeOrDescription(@RequestBody String searchPrefix) {
        Map<String, Object> result = new HashMap<>();
        try {
            depoService.searchByDeptCodeOrDescription(searchPrefix, result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path= "/all-depo", method = RequestMethod.GET)
    public ResponseEntity getallDepos() {
        Map<String, Object> result = new HashMap<>();
        try {
            depoService.getAllDepos(result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
