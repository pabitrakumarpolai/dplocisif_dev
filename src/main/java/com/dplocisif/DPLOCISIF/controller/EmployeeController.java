package com.dplocisif.DPLOCISIF.controller;

import com.dplocisif.DPLOCISIF.service.EmployeeService;
import com.dplocisif.DPLOCISIF.startupdto.MenuModuleInfoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/user")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody HashMap<String, String> userDetails) throws JsonProcessingException {
        HashMap<String, Object> result = new HashMap<>();
        employeeService.loginValidate(userDetails, result);
        if (result.containsKey("error")) {
            return new ResponseEntity<>(result.get("error"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path= "/menu", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity getAllMenuByUser(@RequestBody String object) throws IOException {
        TreeMap<String, Map<String, List<MenuModuleInfoDto>>> result = new TreeMap<>();
        employeeService.getAllMenuByUser(object, result);
        if (result.containsKey("error")) {
            return new ResponseEntity<>(result.get("error"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(path = "/save", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity saveUser(@RequestBody String object) throws JSONException {
        Map<String, Object> result = new HashMap<>();
        employeeService.saveUser(object, result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(path = "/changepassword",method = RequestMethod.POST,produces = "application/json", consumes = "application/json")
    public ResponseEntity changePassword(@RequestBody String object) throws JSONException, JsonProcessingException, SQLException {
        Map<String,Object> result = new HashMap<>();
        employeeService.changePassword(object,result);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(path = "/employee", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity getEmployeeByEmployeeCode(@RequestBody String employeeCode) throws JSONException {
        Map<String, Object> result = new HashMap<>();
        employeeService.getEmployeeByEmployeeCode(employeeCode, result);
        if (result.containsKey("error")) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @RequestMapping(path = "/refresh-token", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity generateToken(@RequestBody String oldToken) throws JSONException {
        Map<String, Object> result = new HashMap<>();
        employeeService.tokenGenerate(oldToken, result);
        if (result.containsKey("error")) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}
