package com.dplocisif.DPLOCISIF.service;

import com.dplocisif.DPLOCISIF.DAO.MenuModuleDAO;
import com.dplocisif.DPLOCISIF.Enum.CoreConstansts;
import com.dplocisif.DPLOCISIF.config.ProcedureConnectionCall;
import com.dplocisif.DPLOCISIF.config.jwtconfig.AuthenticationService;
import com.dplocisif.DPLOCISIF.dplApplicationStartup.ApplicationStartup;
import com.dplocisif.DPLOCISIF.model.EmployeePayDetailsModule;
import com.dplocisif.DPLOCISIF.model.LoginData;
import com.dplocisif.DPLOCISIF.model.MenuUserAccessModule;
import com.dplocisif.DPLOCISIF.repository.EmployeePayDetailsRepository;
import com.dplocisif.DPLOCISIF.repository.EmployeeRepository;
import com.dplocisif.DPLOCISIF.repository.IndentApproverRepository;
import com.dplocisif.DPLOCISIF.repository.MenuUserAccessModuleRepository;
import com.dplocisif.DPLOCISIF.repository.ProcedureRepository.EmployeeProcedureRepository;
import com.dplocisif.DPLOCISIF.startupdto.AllEmployeeDTO;
import com.dplocisif.DPLOCISIF.startupdto.MenuModuleInfoDto;
import com.dplocisif.DPLOCISIF.startupdto.MenuRoleModuleDTO;
import com.dplocisif.DPLOCISIF.transformer.MenuModuleTransformer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;
import java.util.function.Function;

/**
 * EmployeeService provides functionalities related to employee management, authentication, authorization, and password management.
 * It contains methods for login validation, retrieving menus by user, saving users, changing passwords, and retrieving employee details.
 */

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    ApplicationStartup applicationStartup;
    @Autowired
    ProcedureConnectionCall procedureConnectionCall;
    @Autowired
    MenuUserAccessModuleRepository menuUserAccessModuleRepository;
    @Autowired
    MenuModuleDAO menuModuleDAO;
    @Autowired
    EmployeeProcedureRepository employeeProcedureRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Value("${jwt.secretKey}")
    private String SECRET_KEY;
    /**
     * Validates user login credentials and generates authentication token upon successful login.
     * @param userDetails A HashMap containing user credentials (userName, password).
     * @param result A Map to store the login validation result.
     * @throws JsonProcessingException If there is an error during JSON processing.
     */

    public void loginValidate(HashMap<String, String> userDetails, Map<String, Object> result) throws JsonProcessingException {
        String userName = userDetails.get("userName");
        String password = userDetails.get("password");
        Optional<List<Object[]>> userLoginModuleOptional = Optional.ofNullable(employeeRepository.findUserByLoginNameAndPassword(userName, password));
        if (userLoginModuleOptional.isEmpty() || userLoginModuleOptional.get().isEmpty()) {
            result.put("error", "UserName or Password is Wrong");
        } else {
            LoginData loginData = convertIntoLoginObject(userLoginModuleOptional.get().get(0));
            String pass = loginData.getPassword();
            if (StringUtils.isBlank(userName)) {
                result.put("error", "User name Must not be Blank");
            } else if (!pass.equals(password)) {
                result.put("error", "Password is Wrong");
            }
            if (!StringUtils.isBlank(userName)) {
                String token = authenticationService.generateToken(userName);
                result.put("token", token);
                result.put("success", "Login is Successful");
                result.put("loginId", loginData.getLogin_id());
                result.put("roleId", loginData.role_id);
                result.put("NGS", loginData.ngs);
                result.put("loginName",loginData.loginName);
            }
        }
    }

    private LoginData convertIntoLoginObject(Object[] objects) {
        return LoginData.builder()
                .companyMarker((BigDecimal) objects[0])
                .login_id((BigDecimal) objects[1])
                .role_id((BigDecimal) objects[2])
                .loginName((String) objects[3])
                .password((String) objects[4])
                .ngs((String) objects[5])
                .empName(StringUtils.isBlank((String) objects[6]) ? "" : (String) objects[6])
                .companyName((String) objects[7])
                .build();
    }

    /**
     * Retrieves all menus accessible to a user based on their login.
     * @param object JSON string containing user login data.
     * @param result A TreeMap to store the menu information.
     * @throws IOException If there is an error during IO operations.
     */

    public void getAllMenuByUser(String object, TreeMap<String, Map<String, List<MenuModuleInfoDto>>> result) throws IOException {
        LoginData loginData = objectMapper.readValue(object, LoginData.class);
        BigDecimal loginId = loginData.getLogin_id();
        Optional<List<MenuModuleInfoDto>> optionalMenuAccordingToUser = Optional.ofNullable(applicationStartup.listOfObjectAccordingToUserLoginId(loginId));
        if (optionalMenuAccordingToUser.isEmpty()) {
            result.put("error", new HashMap<>());
        }else {
            HashSet<String> uniqueMenuSet = new HashSet<>();
            List<MenuModuleInfoDto> menuAccordingToUser = optionalMenuAccordingToUser.get();
            menuAccordingToUser.forEach(menu -> {
                if (result.containsKey(menu.getParentModuleName()) && !uniqueMenuSet.contains(menu.getMenuName())) {
                    Map<String, List<MenuModuleInfoDto>> existingMap = result.get(menu.getParentModuleName());
                    if (existingMap.containsKey(menu.getModuleName())) {
                        List<MenuModuleInfoDto> existingMenu = existingMap.get(menu.getModuleName());
                        existingMenu.add(menu);
                        existingMap.put(menu.getModuleName(), existingMenu);
                        uniqueMenuSet.add(menu.getMenuName());
                    }
                    result.put(menu.getParentModuleName(), existingMap);
                }else if(!uniqueMenuSet.contains(menu.getMenuName())){
                    Map<String, List<MenuModuleInfoDto>> childModuleMap = new HashMap<>();
                    List<MenuModuleInfoDto> menus = new ArrayList<>();
                    menus.add(menu);
                    childModuleMap.put(menu.getModuleName(), menus);
                    result.put(menu.getParentModuleName(), childModuleMap);
                    uniqueMenuSet.add(menu.getMenuName());
                }
            });
        }

    }

    /**
     * Saves a new user along with their menu access privileges.
     * @param object JSON string containing user details and menu access data.
     * @param result A Map to store the operation result.
     * @throws JSONException If there is an error during JSON processing.
     */

    public void saveUser(String object, Map<String, Object> result) throws JSONException {
        JSONObject jsonObject = new JSONObject(object);//in data we need to have the List of MenuUserAccessModule
        Long companyMarker = Long.valueOf(CoreConstansts.DEFAULT_COMPANY_MARKER.getValue());
        String ngs = String.valueOf(jsonObject.get("ngs"));
        String loginName = String.valueOf(jsonObject.get("loginName"));
        String password = String.valueOf(jsonObject.get("password"));
        Long roleId = jsonObject.getLong("roleId");
        List<MenuRoleModuleDTO> allMenuByRoleId = menuModuleDAO.getAllMenuByRole(String.valueOf(roleId));
        HashMap<Long, MenuRoleModuleDTO> menuIdToMenuHashMap = new HashMap<>();
        prepareMenuIdToMenuHashMap(allMenuByRoleId, menuIdToMenuHashMap);
        Integer callCreateLoginProcedureResponse = employeeProcedureRepository.createLogin(companyMarker, loginName, ngs, password, roleId);
        if (callCreateLoginProcedureResponse != -1) {
            JSONArray jsonArray = (JSONArray) jsonObject.get("data");
            Type listType = new TypeReference<List<MenuUserAccessModule>>() {}.getType();
            List<MenuUserAccessModule> includeMenuUserAccessModuleList = new Gson().fromJson(jsonArray.toString(), listType);
            HashMap<Long, MenuRoleModuleDTO> includeMenuModuleMap = new HashMap<>();
            prepareMenuIdToMenuHashMap(includeMenuUserAccessModuleList, includeMenuModuleMap, menuIdToMenuHashMap);
            List<MenuUserAccessModule> excludeMenus = new ArrayList<>();
            for (Map.Entry<Long, MenuRoleModuleDTO> menuModuleEntry : menuIdToMenuHashMap.entrySet()) {
                Long key = menuModuleEntry.getKey();
                MenuRoleModuleDTO menuModule = menuModuleEntry.getValue();
                if (!includeMenuModuleMap.containsKey(key)) {
                    excludeMenus.add(MenuModuleTransformer.menuRoleModuleToMenuUserAccessModule(menuModule, callCreateLoginProcedureResponse.longValue()));
                }
            }
            menuUserAccessModuleRepository.saveAll(excludeMenus);
            result.put("success", "User have been successfully added");
            applicationStartup.startUp();
        } else {
            result.put("error", "Duplicate User Login Name");
        }
    }

    private void prepareMenuIdToMenuHashMap(List<MenuUserAccessModule> includeMenuUserAccessModuleList, HashMap<Long, MenuRoleModuleDTO> includeMenuModuleMap, HashMap<Long, MenuRoleModuleDTO> menuIdToMenuHashMap) {
        includeMenuUserAccessModuleList.forEach(menu -> {
            if (menuIdToMenuHashMap.containsKey(menu.getMenuId())) {
                MenuRoleModuleDTO menuModule = menuIdToMenuHashMap.get(menu.getMenuId());
                includeMenuModuleMap.put(menu.getMenuId(), menuModule);
            }
        });
    }

    private void prepareMenuIdToMenuHashMap(List<MenuRoleModuleDTO> allMenuByRoleId, HashMap<Long, MenuRoleModuleDTO> menuIdToMenuHashMap) {
        allMenuByRoleId.forEach(menu -> menuIdToMenuHashMap.put(Long.parseLong(menu.getMenuId()), menu));
    }

    /**
     * Retrieves employee details based on employee code.
     * @param employeeCode JSON string containing employee code.
     * @param result A Map to store the operation result.
     * @throws JSONException If there is an error during JSON processing.
     */

    public void getEmployeeByEmployeeCode(String employeeCode, Map<String, Object> result) throws JSONException {
        JSONObject jsonObject = new JSONObject(employeeCode);
        String empCode = jsonObject.has("empCode") ? (String) jsonObject.get("empCode") : "";
        if (StringUtils.isBlank(empCode)) {
            result.put("error", "Employee Code Should Not Be Blank");
        } else {
            HashMap<String, AllEmployeeDTO> allEmployeeDTOHashMap = applicationStartup.getEmployeeIdToEmployeeDtoMap();
            if (!allEmployeeDTOHashMap.containsKey(empCode)) {
                result.put("error", "Invalid Employee Id");
            } else {
                result.put("success", allEmployeeDTOHashMap.get(empCode));
            }
        }
    }

    /**
     * Changes user password.
     * @param object JSON string containing login name and old/new passwords.
     * @param result A Map to store the operation result.
     * @throws JSONException If there is an error during JSON processing.
     * @throws JsonProcessingException If there is an error during JSON processing.
     * @throws SQLException If there is an error during SQL operation.
     */

    public void changePassword(String object, Map<String, Object> result) throws JSONException, JsonProcessingException, SQLException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JSONObject jsonObject = new JSONObject(object);
        String loginName = jsonObject.getString("loginName");
        String oldPassword = jsonObject.getString("oldPassword");
        String newPassword = jsonObject.getString("newPassword");
        String confirmNewPassword = jsonObject.getString("confirmNewPassword");
        if (!confirmNewPassword.equals(newPassword)) result.put("error", "Confirm Password Does not Matched");
        else {
            CallableStatement csmt = procedureConnectionCall.getConnection("{call DCST_RESET_PWD_PG.update_userId_pr(?,?,?,?)}");
            csmt.setString(1, loginName);
            csmt.setString(2, oldPassword);
            csmt.setString(3, newPassword);
            csmt.registerOutParameter(4, Types.INTEGER);
            csmt.executeQuery();
            boolean status = csmt.getBoolean(4);
            if (status) {
                result.put("success", "updated successfully");
            } else {
                result.put("error", "Password is not Updated");
            }
        }
    }

    public void tokenGenerate(String data, Map<String, Object> result) throws JSONException {
        JSONObject jsonObject = new JSONObject(data);
        String oldToken = jsonObject.has("token") ? jsonObject.getString("token") : "";
        String userName = jsonObject.has("userName") ? jsonObject.getString("userName") : "";
        if (StringUtils.isBlank(oldToken) || StringUtils.isBlank(userName)) throw new RuntimeException("Token And UserName must Be present");

        String tokenUserName = extractUserName(oldToken);

        if (tokenUserName.equals(userName)) {
            String token = authenticationService.generateToken(userName);
            result.put("token", token);
        } else {
            result.put("error", "Wrong Token");
        }
    }

    public String extractUserName(String token) {
        // Extract and return the subject claim from the token
        return extractClaim(token, Claims::getSubject);
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        // Extract the specified claim using the provided function
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        // Parse and return all claims from the token
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes(Charset.forName("UTF-8")))
                    .build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException exception) {
            return exception.getClaims();
        }
    }

}