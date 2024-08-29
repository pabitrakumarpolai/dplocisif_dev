package com.dplocisif.DPLOCISIF.controller;

import com.dplocisif.DPLOCISIF.service.DcstTransactionService;
import org.springframework.data.domain.PageRequest;
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
public class DcstTransactionController {

    private final DcstTransactionService dcstTransactionService;

    public DcstTransactionController(DcstTransactionService dcstTransactionService) {
        this.dcstTransactionService = dcstTransactionService;
    }

    @RequestMapping(path= "/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity addDsctTransaction(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            dcstTransactionService.addDsctTransaction(object, result);
            if (result.containsKey("error")) {
                return new ResponseEntity<>(result.get("error"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path= "/update", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity updateDcstTransaction(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            dcstTransactionService.updateDcstTransaction(object, result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception exception) {

            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path= "/search", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity searchDcstTransaction(@RequestBody String searchPrefix) {
        Map<String, Object> result = new HashMap<>();
        try {
            dcstTransactionService.searchDcstTransaction(searchPrefix, result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception exception) {

            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

}
