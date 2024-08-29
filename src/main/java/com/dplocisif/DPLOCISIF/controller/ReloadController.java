package com.dplocisif.DPLOCISIF.controller;

import com.dplocisif.DPLOCISIF.service.ReloadService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/admin")
public class ReloadController {
    private final Logger logger = Logger.getLogger(ReloadController.class.getName());
    @Autowired
    ReloadService reloadService;

    @RequestMapping(path= "/reload", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity reloadServlet(@RequestBody String object) {
        Map<String, String> result = new HashMap<>();
        try {
            reloadService.reloadServlet(result, object);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (JSONException exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
