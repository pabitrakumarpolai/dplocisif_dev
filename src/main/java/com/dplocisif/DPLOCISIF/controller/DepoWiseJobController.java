package com.dplocisif.DPLOCISIF.controller;

import com.dplocisif.DPLOCISIF.repository.DsctDepoRepository;
import com.dplocisif.DPLOCISIF.service.DepoWiseJobService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/depowise")
public class DepoWiseJobController {
    @Autowired
    DepoWiseJobService depoWiseJobService;
    @Autowired
    DsctDepoRepository dsctDepoRepository;
//    @GetMapping("/depolist")
//    public ResponseEntity getAllDepoCodes(){
//        HashMap<String, Object> result = new HashMap<>();
//        dsctDepoRepository.getAllDepoCodes(result);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }


    @RequestMapping(path= "/add-job", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity addAllDepoWiseJob(@RequestBody String object) throws IOException, ParseException {
        Map<String,Object> result = new HashMap<>();
        try {
            depoWiseJobService.addDepoWiseJob(object, result);
            if (result.containsKey("error")) {
                return new ResponseEntity<>(result.get("error"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/update-job",method = RequestMethod.PUT,produces = "application/json",consumes = "application/json")
    public ResponseEntity upadteDepoWiseJob(@RequestBody String object) throws JSONException, JsonProcessingException {
        Map<String,Object> result = new HashMap<>();
        depoWiseJobService.upadteDepoWiseJob(object,result);
        if (result.containsKey("error")){
            return new ResponseEntity<>(result.get("error"),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(path = "/search-job",method = RequestMethod.POST,produces ="application/json",consumes = "application/json" )
    public ResponseEntity searchDepoWiseJob(@RequestBody String object) throws JSONException {
        Map<String, Object> result = new HashMap<>();
        depoWiseJobService.searchDepoWiseJob(object, result);
        if (result.containsKey("error")) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
