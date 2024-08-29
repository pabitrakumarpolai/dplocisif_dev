package com.dplocisif.DPLOCISIF.service;


import com.dplocisif.DPLOCISIF.DAO.RoleDAO;
import com.dplocisif.DPLOCISIF.dplApplicationStartup.ApplicationStartup;
import com.dplocisif.DPLOCISIF.exception.LoginIdMissingException;
import com.dplocisif.DPLOCISIF.model.RoleModule;
import com.dplocisif.DPLOCISIF.repository.ProcedureRepository.RoleProcedureRepository;
import com.dplocisif.DPLOCISIF.repository.RoleModuleRepository;
import com.dplocisif.DPLOCISIF.startupdto.MenuRoleAccessDTO;
import io.micrometer.common.util.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleModuleService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(RoleModuleService.class);
    @Autowired
    RoleModuleRepository roleModuleRepository;
    @Autowired
    RoleDAO roleDAO;
    @Autowired
    RoleModuleRepository roleRepository;
    @Autowired
    ApplicationStartup applicationStartup;
    @Autowired
    RoleProcedureRepository roleProcedureRepository;

    /**
     * Retrieves all roles.
     * @param result The map to store the retrieved roles.
     */

    public void getAllRoles(HashMap<String, Object> result) {
        List<RoleModule> savedRoles = roleModuleRepository.findAll();
        result.put("success", savedRoles);
    }

    /**
     * Adds a role description.
     * @param object The JSON object containing the role description and login ID.
     * @param result The map to store the result of the operation.
     * @throws JSONException If there is an error in JSON processing.
     * @throws SQLException If an SQL exception occurs.
     */

    public void addRoleDescription(String object, Map<String, Object> result) throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(object);
        String roleDescription = (String) ((JSONObject)jsonObject.get("data")).get("roleDescription");
        long loginId = ((Integer)((JSONObject)jsonObject.get("data")).get("loginId")).longValue();
        if (loginId == 0) throw new LoginIdMissingException("Login Id Is not Present");

        List<RoleModule> roleModules = roleRepository.findAll();
        List<RoleModule> sameNameRoleList = roleModules.stream().filter(role -> role.getRoleDesc().equalsIgnoreCase(roleDescription)).toList();
        if (!sameNameRoleList.isEmpty()) {
            result.put("error", roleDescription.concat(" Already Present"));
        } else {
//            roleDAO.addRoleDescription(roleDescription, loginId);
            roleProcedureRepository.addRoleDescription(roleDescription, loginId);
        }
    }

    /**
     * Retrieves role descriptions.
     * @param result The map to store the retrieved role descriptions.
     */

    public void getRolesDescrition(Map<String, Object> result) {
        List<RoleModule> roleModules = roleRepository.findAll();
        result.put("success", roleModules);
    }

    /**
     * Retrieves all menus by role ID.
     * @param object The JSON object containing the role ID.
     * @param result The map to store the retrieved menus.
     * @throws JSONException If there is an error in JSON processing.
     * @throws SQLException If an SQL exception occurs.
     */

    public void getAllMenuByRoleId(String object, Map<String, Object> result) throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(object);
        String roleId = jsonObject.has("roleId") ? (String) jsonObject.get("roleId") : "";
//        roleDAO.getLoginMenuRoleAccess(roleId, result);
        Map<String, HashMap<String, List<MenuRoleAccessDTO>>> map = roleProcedureRepository.getMenuRoleAccess(roleId);
        if (!map.isEmpty()) {
            result.put("success", map);
        } else {
            result.put("error", "You Don't Have any menu access");
        }
    }

    /**
     * Saves role menu container.
     * @param result The map to store the result of the operation.
     * @param object The JSON object containing the role ID and menu list.
     * @throws JSONException If there is an error in JSON processing.
     */

    public void saveRoleMenuContainer(Map<String, Object> result, String object) throws JSONException {
        JSONObject jsonObject = new JSONObject(object);
        String roleId = jsonObject.has("roleId") ? jsonObject.getString("roleId") : "";
        String menuList = jsonObject.has("menuList") ? jsonObject.getString("menuList") : "";
        if (StringUtils.isBlank(roleId)) {
            result.put("error", "Select a Role");
        }
        if (!StringUtils.isBlank(roleId) && StringUtils.isBlank(menuList)) {
            result.put("error", "Select At Least One Menu");
        }

        if (!result.containsKey("error")) {
            try {
//                int status = roleDAO.addRoleMenuAccess(roleId, menuList);
                roleProcedureRepository.addRoleMenuAccess(roleId, menuList);
                result.put("success", "Menu Have Been Assigned Successfully");
                applicationStartup.startUp();
            } catch (SQLException e) {
                throw new RuntimeException("Something Went Wrong in Add Role");
            }
        }
    }
}
