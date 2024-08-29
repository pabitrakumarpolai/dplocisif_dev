package com.dplocisif.DPLOCISIF.controller;

import com.dplocisif.DPLOCISIF.model.MenuModule;
import com.dplocisif.DPLOCISIF.service.MenuService;
import com.dplocisif.DPLOCISIF.startupdto.MenuRoleModuleDTO;
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
import java.util.List;
import java.util.TreeMap;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;
    @RequestMapping(path= "/rolebasedmenu", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity getAllMenuByRole(@RequestBody String object) throws IOException, ParseException {
        TreeMap<String, HashMap<String, List<MenuRoleModuleDTO>>> result = new TreeMap<>();
        menuService.getAllMenuByRole(object, result);
        if (result.containsKey("error")) {
            return new ResponseEntity<>(result.get("error"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
