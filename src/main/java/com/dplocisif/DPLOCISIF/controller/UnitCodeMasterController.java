package com.dplocisif.DPLOCISIF.controller;

import com.dplocisif.DPLOCISIF.service.UnitCodeMasterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/unit-code")
public class UnitCodeMasterController
{
    private final UnitCodeMasterService unitCodeMasterService;

    public UnitCodeMasterController(UnitCodeMasterService unitCodeMasterService) {
        this.unitCodeMasterService = unitCodeMasterService;
    }

    //PostRequest(Save_Data)
    @RequestMapping(path="/add" , method = RequestMethod.POST,produces = "application/json" , consumes = "application/json")
    public ResponseEntity addUnitCodeMaster(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            unitCodeMasterService.addUnitCode(object, result);
            if(result.containsKey("error")) {
                return new ResponseEntity<>(result.get("error") , HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(Exception exception) {
            result.put("error" , exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    //PutRequest(Update_Data)
    @RequestMapping(path= "/update", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity updateUnitCodeMaster(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            unitCodeMasterService.updateUnitCode(object, result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception exception) {
            result.put("error" , exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    //SearchRequest(Searching_Data)
    @RequestMapping(path= "/search", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity searchUnitCodeMaster(@RequestBody String searchPrefix) {
        Map<String, Object> result = new HashMap<>();
        try {
            unitCodeMasterService.searchUnitCode(searchPrefix, result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

  /*  //DeleteRequest(Deleting_Data)
   *//* @RequestMapping(path= "/delete/{unitCode}", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
    public ResponseEntity<Map<String, Object>> deleteUnitCode(@PathVariable("unitCode") long unitCode) {
        Map<String, Object> result = new HashMap<>();
        try {
            unitCodeMasterService.deleteUnitCode(unitCode, result);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }*/


    @RequestMapping(path= "/get-all/unitcode", method = RequestMethod.GET)
    public ResponseEntity getAllUnitCodes() {
        Map<String, Object> result = new HashMap<>();
        try {
            unitCodeMasterService.getAllUnitCodes(result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("error", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

}
