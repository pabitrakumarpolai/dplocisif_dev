package com.dplocisif.DPLOCISIF.dplApplicationStartup;


import com.dplocisif.DPLOCISIF.Enum.CoreConstansts;
import com.dplocisif.DPLOCISIF.Enum.DPLPAYConstants;
import com.dplocisif.DPLOCISIF.config.ProcedureConnectionCall;
import com.dplocisif.DPLOCISIF.model.*;
import com.dplocisif.DPLOCISIF.repository.*;
import com.dplocisif.DPLOCISIF.service.CompanyModuleRepositoryImpl;
import com.dplocisif.DPLOCISIF.service.MenuInfoModuleRepository;
import com.dplocisif.DPLOCISIF.startupdto.*;
import com.dplocisif.DPLOCISIF.transformer.DepartmentModuleTransformer;
import com.dplocisif.DPLOCISIF.transformer.StcdModuleTranformer;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;


@Component
@Order(3)
@Getter
public class ApplicationStartup {
    private List<CompanyModuleInfoDTO> companyModuleInfo;
    private List<MenuModuleInfoDto> menuModuleList;
    private HashMap<Long, CompanyModuleInfoDTO> companyModuleInfoDTOHashMap = new HashMap<>();
    private List<DepartmentMouldeInfoDTO> depertModuleList;
    private List<StcdModuleInfoDTO> stcdModuleList;
    private HashMap<Long, List<MenuModuleInfoDto>> userLogIdToMenuModule = new HashMap<>(); // User loginId -> List Of Menu
    private HashMap<Long, List<MenuModule>> roleToMenuMap = new HashMap<>(); // menu role -> list of menu
    private HashMap<Long, List<Long>> menuIdToRoleIdMap = new HashMap<>(); // menu id -> role id
    private List<MenuRoleModule> menuRoleModules = new ArrayList<>(); // prepare all the menu role
    private HashMap<String, List<UnitCodeMasterModule>> mapBasedOnUnitCodeDescription = new HashMap<>();
    private HashMap<Long, UnitCodeMasterModule> mapBasedUnitCode = new HashMap<>();
    private List<AllEmployeeDTO> allEmployeeList = new ArrayList<>();
    private HashMap<String, AllEmployeeDTO> employeeIdToEmployeeDtoMap = new HashMap<>();
    private final Logger logger = Logger.getLogger(ApplicationStartup.class.getName());
    @Autowired
    CompanyModuleRepositoryImpl companyModuleRepository;
    @Autowired
    DepartmentModuleRepository departmentModuleRepository;
    @Autowired
    StcdModuleRepository stcdModuleRepository;
    @Autowired
    MenuRoleModuleRepository menuRoleModuleRepository;
    @Autowired
    MenuModuleRepository menuModuleRepository;
    @Autowired
    UnitCodeMasterRepository unitCodeMasterRepository;
    @Autowired
    ProcedureConnectionCall procedureConnectionCall;
    @Autowired
    MenuInfoModuleRepository menuInfoModuleRepository;
    @PostConstruct
    public void startUp() {
        logger.info("Start Loading Company Module....");
        companyModuleInfo = companyModuleRepository.getDcpyCompanyDetails();
        logger.info("End Loading Company Module....");
        logger.info("Start Loading Menu Module....");
        menuModuleList = menuInfoModuleRepository.getAllMenuModuleInfoDto();
        fillUserLogIdToMenuModule(menuModuleList);
        logger.info("End Loading Menu Module....");
        logger.info("Start Prepare Company Module Map...");
        prepareCompanyModuleInfoDtoHashMap();
        logger.info("End Prepare Company Module Map...");
        logger.info("start Loading Department Module....");
        depertModuleList = DepartmentModuleTransformer.depertModulesTODepertmentModuleInfo(departmentModuleRepository.findAll());
        logger.info("End Loading Department Module....");
        logger.info("start Loading Salhead(stcd) Module....");
        stcdModuleList = StcdModuleTranformer.stcdModuleToStcdModuleInfoDto(stcdModuleRepository.findAll());
        logger.info("End Loading Salhead(stcd) Module....");
        logger.info("Start Loading Menu Role Module...");
        prepareMenuRoleModule();
        logger.info("End Menu Role Module...");
        logger.info("Start Loading Role Based Menu Module...");
        prepareRoleBasedMenuModule();
        logger.info("End Loading Role Based Menu Module...");
        logger.info("Start Prepare Unit code Master...");
        prepareUnitCodeMasterModule();
        logger.info("End prepare Unit code Master....");
        logger.info("Start Prepare all Employee...");
        prepareAllEmployee();
        logger.info("All Employee Prepared Successfully");
        logger.info("Prepare Employee Map...");
        prepareEmployeeMap();
        logger.info("Ending Employee Map...");
    }

    private void prepareEmployeeMap() {
        allEmployeeList.forEach(emp -> employeeIdToEmployeeDtoMap.putIfAbsent(emp.getEmpId(), emp));
    }

       private void prepareUnitCodeMasterModule() {
        List<UnitCodeMasterModule> unitCodeMasterModuleList = unitCodeMasterRepository.findAll();
        unitCodeMasterModuleList.forEach(uCode -> {
            if(mapBasedOnUnitCodeDescription.containsKey(uCode.getUnitDescription())){
                List<UnitCodeMasterModule> saveUCode = mapBasedOnUnitCodeDescription.get(uCode.getUnitDescription());
                saveUCode.add(uCode);
                mapBasedOnUnitCodeDescription.put(uCode.getUnitDescription(),saveUCode);
            }else {
                List<UnitCodeMasterModule> unitCode = new ArrayList<>();
                unitCode.add(uCode);
                mapBasedOnUnitCodeDescription.put(uCode.getUnitDescription(),unitCode);
            }

            mapBasedUnitCode.putIfAbsent(uCode.getUnitCode(), uCode);
        });
    }


    private void prepareRoleBasedMenuModule() {
        List<MenuModule> menuModules = menuModuleRepository.findAll();
        menuRoleModules.forEach(role -> {
            if (!roleToMenuMap.containsKey(role.getRoleId())) {
                roleToMenuMap.put(role.getRoleId(), new ArrayList<>());
            }
            if (menuIdToRoleIdMap.containsKey(role.getMenuId())) {
                List<Long> roleIdList = menuIdToRoleIdMap.get(role.getMenuId());
                roleIdList.add(role.getRoleId());
                menuIdToRoleIdMap.put(role.getMenuId(), roleIdList);
            } else {
                List<Long> roleIdList = new ArrayList<>();
                roleIdList.add(role.getRoleId());
                menuIdToRoleIdMap.put(role.getMenuId(), roleIdList);
            }
        });

        menuModules.forEach(menu -> {
//            if (menu.getModuleMaster().getModuleName().equals("Security Module")) {
//                switch(menu.getMenuName()) {
//                    case "Creation of User" :
//                        menu.setMenuLinkName("/user/creation");
//                        break;
//
//                    case "Portal Admin" :
//                        menu.setMenuLinkName("/user/portal-admin");
//                        break;
//
//                    case "Change Login Password" :
//                        menu.setMenuLinkName("/user/change-password");
//                        break;
//
//                    case "Reset Login Password" :
//                        menu.setMenuLinkName("/user/reset-password");
//                        break;
//
//                    case "Creation of Roles" :
//                        menu.setMenuLinkName("/user/role-creation");
//                        break;
//                }
//            }
            Long menuId = menu.getMenuId();
            if (menuIdToRoleIdMap.containsKey(menuId)) {
                List<Long> roleIdList = menuIdToRoleIdMap.get(menuId);
                roleIdList.forEach(roleId -> {
                    List<MenuModule> menuModuleList = roleToMenuMap.get(roleId);
                    menuModuleList.add(menu);
                    roleToMenuMap.put(roleId, menuModuleList);
                });
            }
        });
    }

    private void prepareMenuRoleModule() {
        menuRoleModules.addAll(menuRoleModuleRepository.findAll());
    }

    private void fillUserLogIdToMenuModule(List<MenuModuleInfoDto> menuModuleList) {
        menuModuleList.forEach(menu -> {
            long loginId = menu.getLoginId();
//            if (menu.getModuleName().equals("Transaction")) {
//                switch(menu.getMenuName()) {
//                    case "Creation of User" :
//                        menu.setMenuLinkName("/user/creation");
//                        break;
//
//                    case "Portal Admin" :
//                        menu.setMenuLinkName("/user/portal-admin");
//                        break;
//
//                    case "Change Login Password" :
//                        menu.setMenuLinkName("/user/change-password");
//                        break;
//
//                    case "Reset Login Password" :
//                        menu.setMenuLinkName("/user/reset-password");
//                        break;
//
//                    case "Creation of Roles" :
//                        menu.setMenuLinkName("/user/role-creation");
//                        break;
//                }
//            }
                if (userLogIdToMenuModule.containsKey(loginId)) {
                    List<MenuModuleInfoDto> exitingMenus = userLogIdToMenuModule.get(loginId);
                    exitingMenus.add(menu);
                    userLogIdToMenuModule.put(loginId, exitingMenus);
                } else {
                    List<MenuModuleInfoDto> menuModuleInfoDtos = new ArrayList<>();
                    menuModuleInfoDtos.add(menu);
                    userLogIdToMenuModule.put(loginId, menuModuleInfoDtos);
                }
        });
    }

    public List<MenuModuleInfoDto> listOfObjectAccordingToUserLoginId(BigDecimal userLoginId) {
        if (userLogIdToMenuModule.containsKey(userLoginId.longValue())) return userLogIdToMenuModule.get(userLoginId.longValue());
        return Collections.emptyList();
    }
    public List<MenuModule> getMenuByRole(Long role) {
        return roleToMenuMap.get(role);
    }
    public void prepareCompanyModuleInfoDtoHashMap() {
        companyModuleInfo.forEach(company -> {
            if (!companyModuleInfoDTOHashMap.containsKey(company.getCompanyMarker())) {
                companyModuleInfoDTOHashMap.put(company.getCompanyMarker(), company);
            }
        });
    }
    public CompanyModuleInfoDTO getCompanyModule(int companyMarker) {
        if (companyModuleInfoDTOHashMap.containsKey(Long.valueOf(companyMarker))) {
            return companyModuleInfoDTOHashMap.get(Long.valueOf(companyMarker));
        }
        return null;
    }

    public void prepareAllEmployee() {
        ResultSet resultSet;
        try {
            int month = getSalMonth(CoreConstansts.DEFAULT_COMPANY_MARKER.getValue());
            int year = getSalYear(CoreConstansts.DEFAULT_COMPANY_MARKER.getValue());
            int companyMarker = CoreConstansts.DEFAULT_COMPANY_MARKER.getValue();
            CallableStatement callableStatement = procedureConnectionCall.getConnection("{call dcpy_master_pg.get_allemp_list_pr(?,?,?,?)}");
            callableStatement.setInt(1, companyMarker);
            callableStatement.setInt(2, month);
            callableStatement.setInt(3, year);
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(4);
            allEmployeeList =  prepareResult(resultSet);
        } catch (SQLException exception) {
            logger.info(exception.getMessage());
        }
    }

    private List<AllEmployeeDTO> prepareResult(ResultSet resultSet) throws SQLException {
        ArrayList<AllEmployeeDTO> list = new ArrayList<>();
        while (resultSet.next()) {
            AllEmployeeDTO objAllEmpVO = new AllEmployeeDTO();
            objAllEmpVO.setEmpId(avoidNull(resultSet.getString("EMP_ID")));
            objAllEmpVO.setEmpName(avoidNull(resultSet.getString("EMP_NAME")));
            objAllEmpVO.setNds(avoidNull(resultSet.getString("NDS")));
            objAllEmpVO.setNpost(avoidNull(resultSet.getString("NPOST")));
            objAllEmpVO.setPosting(avoidNull(resultSet.getString("POSTING")));
            objAllEmpVO.setDegn(avoidNull(resultSet.getString("DEGN")));
            objAllEmpVO.setDesignation(avoidNull(resultSet.getString("DESIGNATION")));
            objAllEmpVO.setCcsno(avoidNull(resultSet.getString("CCSNO")));
            objAllEmpVO.setMcsno(avoidNull(resultSet.getString("MCSNO")));
            objAllEmpVO.setEmpType(avoidNull(resultSet.getString("EMP_TYPE")));
            objAllEmpVO.setNoOfDays(avoidNull(resultSet.getString("NO_OF_DAYS")));
            list.add(objAllEmpVO);
        }
        return list;
    }

    public int getSalMonth(int companyMarker) {
        long salMonth = 0;
        CompanyModuleInfoDTO dplpayCompanyData = companyModuleInfoDTOHashMap.get(Long.valueOf(companyMarker));
        salMonth = dplpayCompanyData.getLastPayMonth();
        if (salMonth != Long.parseLong(DPLPAYConstants.DECEMBER_MONTH.getValue().toString())) {
            salMonth = salMonth + 1;
        } else {
            salMonth = (long) DPLPAYConstants.JANUARY_MONTH.getValue();
        }
        return (int) salMonth;
    }

    public int getSalYear(int companyMarker) {
        long salMonth = 0;
        long salYear = 0;
        CompanyModuleInfoDTO dplpayCompanyData = companyModuleInfoDTOHashMap.get(Long.valueOf(companyMarker));
        salMonth = dplpayCompanyData.getLastPayMonth();
        salYear = dplpayCompanyData.getLastPayYear();
        if (salMonth == Long.parseLong(DPLPAYConstants.DECEMBER_MONTH.getValue().toString())) {
            salYear = salYear + 1;
        }
        return (int) salYear;
    }

    public String avoidNull(String inString) {
        if (StringUtils.isBlank(inString)) {
            return "";
        } else {
            return inString;
        }
    }
}
