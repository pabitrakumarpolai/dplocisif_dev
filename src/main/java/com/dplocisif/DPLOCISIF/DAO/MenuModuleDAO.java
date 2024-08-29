package com.dplocisif.DPLOCISIF.DAO;

import com.dplocisif.DPLOCISIF.config.ViewConnection;
import com.dplocisif.DPLOCISIF.startupdto.MenuRoleModuleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class MenuModuleDAO {
    @Autowired
    ViewConnection viewConnection;
    private final Logger logger = Logger.getLogger(MenuModuleDAO.class.getName());

    public List<MenuRoleModuleDTO> getAllMenuByRole(String roleId) {
        String query = "select distinct parent_module_name, parent_module_id, module_id, module_name, menu_id, menu_name, menu_link_name from vw_DCST_menu_access where role_id=" + roleId + " order by module_id, menu_id";
        ResultSet resultSet = viewConnection.getConnectionWithResultSet(query);
        List<MenuRoleModuleDTO> result = new ArrayList<>();
        prepareResult(resultSet, result);
        viewConnection.freeConnection();
        return result;
    }

    private void prepareResult(ResultSet resultSet, List<MenuRoleModuleDTO> result) {
        try {
            while (resultSet.next()) {
                result.add(MenuRoleModuleDTO.builder()
                        .parentModuleName(resultSet.getString("parent_module_name"))
                                .parentModuleId(resultSet.getString("parent_module_id"))
                                .menuId(resultSet.getString("menu_id"))
                                .moduleId(resultSet.getString("module_id"))
                                .menuName(resultSet.getString("menu_name"))
                                .menuLinkName(resultSet.getString("menu_link_name"))
                                .moduleName(resultSet.getString("module_name"))
                        .build());
            }
        }catch (SQLException exception) {
            logger.info(exception.getMessage());
        }
    }
}
