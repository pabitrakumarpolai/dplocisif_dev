package com.dplocisif.DPLOCISIF.DAO;

import com.dplocisif.DPLOCISIF.config.ProcedureConnectionCall;
import com.dplocisif.DPLOCISIF.service.RoleModuleService;
import com.dplocisif.DPLOCISIF.startupdto.MenuRoleAccessDTO;
import io.micrometer.common.util.StringUtils;
import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

@Service
public class RoleDAO {
    @Autowired
    ProcedureConnectionCall procedureConnectionCall;

    public void addRoleDescription(String roleDescription, Long loginId) throws SQLException {
        if (!StringUtils.isBlank(roleDescription)) {
            CallableStatement callableStatement = null;
            try {
                callableStatement = procedureConnectionCall.getConnection("{call dcst_dpl_master_pg.set_role_pr(?,?) }");
                callableStatement.setString(1, roleDescription);
                callableStatement.setLong(2, loginId);
                callableStatement.execute();
            } catch (SQLException exception) {
                throw new RuntimeException(exception);
            } finally {
                if (callableStatement != null) {
                    procedureConnectionCall.closeAllConnection(callableStatement.getConnection(), callableStatement);
                }
            }
        }
    }

    public void getLoginMenuRoleAccess(String roleId, Map<String, Object> result) throws SQLException {
        List<MenuRoleAccessDTO> menuRoleAccessList = new ArrayList<>();
        if (!StringUtils.isBlank(roleId)) {
            CallableStatement callableStatement = null;
            ResultSet resultSet = null;
            try {
                callableStatement = procedureConnectionCall.getConnection("{call dcst_dpl_master_pg.get_menu_role_access_pr(?,?) }");
                callableStatement.setString(1, roleId);
                callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
                callableStatement.execute();
                resultSet = (ResultSet) callableStatement.getObject(2);
                prepareResult(resultSet, menuRoleAccessList);
            } catch (SQLException exception) {
                throw new RuntimeException(exception.getMessage());
            } finally {
                if (callableStatement != null) {
                    procedureConnectionCall.closeAllConnection(callableStatement.getConnection(), callableStatement, resultSet);
                }
            }
            Map<String, HashMap<String, List<MenuRoleAccessDTO>>> map = new TreeMap<>();
            menuRoleAccessList.forEach(role -> {
                if (map.containsKey(role.getParentModuleName())) {
                    HashMap<String, List<MenuRoleAccessDTO>> menuAgainstRoleMap = map.get(role.getParentModuleName());
                    if (menuAgainstRoleMap.containsKey(role.getModuleName())) {
                        List<MenuRoleAccessDTO> menuRoleAccessDTOList = menuAgainstRoleMap.get(role.getModuleName());
                        menuRoleAccessDTOList.add(role);
                        menuAgainstRoleMap.put(role.getModuleName(), menuRoleAccessDTOList);
                    } else {
                        List<MenuRoleAccessDTO> menuRoleAccessDTOList = new ArrayList<>();
                        menuRoleAccessDTOList.add(role);
                        menuAgainstRoleMap.put(role.getModuleName(), menuRoleAccessDTOList);
                    }
                    map.put(role.getParentModuleName(), menuAgainstRoleMap);
                } else {
                    HashMap<String, List<MenuRoleAccessDTO>> menuAgainstRoleMap = new HashMap<>();
                    List<MenuRoleAccessDTO> menuRoleAccessDTOList = new ArrayList<>();
                    menuRoleAccessDTOList.add(role);
                    menuAgainstRoleMap.put(role.getModuleName(), menuRoleAccessDTOList);
                    map.put(role.getParentModuleName(), menuAgainstRoleMap);
                }
            });
            result.put("success", map);
        }
    }

    private void prepareResult(ResultSet resultSet, List<MenuRoleAccessDTO> menuRoleAccessList) {
        try {
            while (resultSet.next()) {
                menuRoleAccessList.add(
                        MenuRoleAccessDTO.builder()
                                .isAccess(resultSet.getString("is_access"))
                                .menuId(resultSet.getLong("menu_id"))
                                .menuName(resultSet.getString("menu_name"))
                                .moduleName(resultSet.getString("module_name"))
                                .moduleId(resultSet.getLong("module_id"))
                                .menuReportHeading(resultSet.getString("menu_report_heading"))
                                .reportPdf(resultSet.getString("report_pdf"))
                                .reportTxt(resultSet.getString("report_txt"))
                                .reportXls(resultSet.getString("report_xls"))
                                .parentModuleId(resultSet.getLong("parent_module_id"))
                                .parentModuleName(resultSet.getString("parent_module_name"))
                                .build()
                );
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public int addRoleMenuAccess(String roleId, String menuList) throws SQLException {
        int status = 1;
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = procedureConnectionCall.getConnection("{call dcst_dpl_master_pg.set_menu_role_access_pr(?,?)}");
            connection = callableStatement.getConnection();
            connection.setAutoCommit(false);
            callableStatement.setString(1, roleId);
            callableStatement.setClob(2, new StringReader(menuList));
            callableStatement.executeQuery();
            connection.commit();
        } catch (SQLException exception) {
            if (connection != null) connection.rollback();
            status = 0;
            throw new RuntimeException(exception);
        } finally {
            if (callableStatement != null) {
                procedureConnectionCall.closeAllConnection(callableStatement.getConnection(), callableStatement);
            }
        }
        return status;
    }
}
