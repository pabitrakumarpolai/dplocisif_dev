package com.dplocisif.DPLOCISIF.controller;

import com.dplocisif.DPLOCISIF.service.RoleModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleModuleController {
    @Autowired
    RoleModuleService roleModuleService;
    @GetMapping("/allroles")
    public ResponseEntity getAllRoles() {
        HashMap<String, Object> result = new HashMap<>();
        roleModuleService.getAllRoles(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/addrole", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity addRoleDescription(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            roleModuleService.addRoleDescription(object, result);
            if (!result.containsKey("error")) {
                result.put("success", "Role Added Successfully");
                return new ResponseEntity(result, HttpStatus.OK);
            } else {
                return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/getroles", method = RequestMethod.GET)
    public ResponseEntity getRoles() {
        Map<String, Object> result = new HashMap<>();
        try {
            roleModuleService.getRolesDescrition(result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/getmenubyrole", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity getAllMenusByRoleId(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            roleModuleService.getAllMenuByRoleId(object, result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            result.put("error", exception.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/save/menu/roleaccess", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity saveRoleMenuContainer(@RequestBody String object) {
        Map<String, Object> result = new HashMap<>();
        try {
            roleModuleService.saveRoleMenuContainer(result, object);
            if (result.containsKey("error")) {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
