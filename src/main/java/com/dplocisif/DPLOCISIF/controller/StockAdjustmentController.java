package com.dplocisif.DPLOCISIF.controller;

import com.dplocisif.DPLOCISIF.service.StockAdjustmentService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stock")
public class StockAdjustmentController {
    @Autowired
    StockAdjustmentService stockAdjustmentService;

    @RequestMapping(path= "/get-by-depoCode", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity getItemByDepoCode(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            stockAdjustmentService.getItemByDepoCode(object, result);
            return result.containsKey("error") ? new ResponseEntity<>(result, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(path= "/get-by-adjusted", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity getAllEmpList(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            stockAdjustmentService.getEmpByLoginId(object, result);
            return result.containsKey("error") ? new ResponseEntity<>(result, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(path= "/get-All-depoCodes", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity getAllDepoCodeList(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            stockAdjustmentService.getAllDepoList(object, result);
            return result.containsKey("error") ? new ResponseEntity<>(result, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(path= "/created", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity createStockAdjustment(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            stockAdjustmentService.createStockAdjustment(object, result);
            return result.containsKey("error") ? new ResponseEntity<>(result, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(path= "/updated", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity updateStock(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            stockAdjustmentService.updateStock(object, result);
            return result.containsKey("error") ? new ResponseEntity<>(result, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(path = "/search",method = RequestMethod.POST,produces = "application/json", consumes = "application/json")
    public ResponseEntity search(@RequestBody String object){
        Map<String,Object> result = new HashMap<>();
        try {
            stockAdjustmentService.searchStock(result, object);
            return result.containsKey("error") ? new ResponseEntity<>(result, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(path = "/delete",method = RequestMethod.DELETE,produces = "application/json", consumes = "application/json")
    public ResponseEntity delete(@RequestBody String object){
        Map<String,Object> result = new HashMap<>();
        try {
            stockAdjustmentService.deleteStock(object, result);
            return result.containsKey("error") ? new ResponseEntity<>(result, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
