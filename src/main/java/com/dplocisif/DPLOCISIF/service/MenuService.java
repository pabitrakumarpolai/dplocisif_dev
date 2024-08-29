package com.dplocisif.DPLOCISIF.service;

import com.dplocisif.DPLOCISIF.DAO.MenuModuleDAO;
import com.dplocisif.DPLOCISIF.startupdto.MenuRoleModuleDTO;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * Service class for managing menu operations.
 */

@Service
public class MenuService {
    @Autowired
    MenuModuleDAO menuModuleDAO;

    /**
     * Retrieves all menus based on the given role.
     * @param object The JSON object containing the roleId.
     * @param result The result map containing the menu hierarchy.
     */

    public void getAllMenuByRole(String object, TreeMap<String, HashMap<String, List<MenuRoleModuleDTO>>> result) {
        JsonObject jsonObject = JsonParser.parseString(object).getAsJsonObject();
        Long roleId = jsonObject.get("roleId").getAsLong();
        List<MenuRoleModuleDTO> menuModuleList = menuModuleDAO.getAllMenuByRole(String.valueOf(roleId));
        menuModuleList.forEach(menuModule -> {
            if (result.containsKey(menuModule.getParentModuleName())) {
                HashMap<String, List<MenuRoleModuleDTO>> childModuleMap = result.get(menuModule.getParentModuleName());
                if (childModuleMap.containsKey(menuModule.getModuleName())) {
                    List<MenuRoleModuleDTO> menuList = childModuleMap.get(menuModule.getModuleName());
                    menuModule.setRoleId(String.valueOf(roleId));
                    menuList.add(menuModule);
                    childModuleMap.put(menuModule.getModuleName(), menuList);
                } else {
                    List<MenuRoleModuleDTO> menuList = new ArrayList<>();
                    menuModule.setRoleId(String.valueOf(roleId));
                    menuList.add(menuModule);
                    childModuleMap.put(menuModule.getModuleName(), menuList);
                }
                result.put(menuModule.getParentModuleName(), childModuleMap);
            } else {
                HashMap<String, List<MenuRoleModuleDTO>> menuROleModuleMap = new HashMap<>();
                List<MenuRoleModuleDTO> menuModules = new ArrayList<>();
                menuModule.setRoleId(String.valueOf(roleId));
                menuModules.add(menuModule);
                menuROleModuleMap.put(menuModule.getModuleName(), menuModules);
                result.put(menuModule.getParentModuleName(), menuROleModuleMap);
            }
        });
    }
}
