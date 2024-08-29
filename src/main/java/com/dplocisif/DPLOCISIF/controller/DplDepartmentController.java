package com.dplocisif.DPLOCISIF.controller;

import com.dplocisif.DPLOCISIF.service.DplDepartmentService;
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
@RequestMapping("/api")
public class DplDepartmentController {
    @Autowired
    DplDepartmentService dplDepartmentService;
    @RequestMapping(path= "/alldepartment", method = RequestMethod.GET)
    public ResponseEntity getallDepartment() {
        Map<String, Object> result = new HashMap<>();
        try {
            dplDepartmentService.getAllDepartment(result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

}
