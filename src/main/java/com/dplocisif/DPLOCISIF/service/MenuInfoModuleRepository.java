package com.dplocisif.DPLOCISIF.service;

import com.dplocisif.DPLOCISIF.config.ViewConnection;
import com.dplocisif.DPLOCISIF.dplApplicationStartup.ApplicationStartup;
import com.dplocisif.DPLOCISIF.startupdto.MenuModuleInfoDto;
import com.dplocisif.DPLOCISIF.transformer.MenuModuleTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class MenuInfoModuleRepository {
    @Autowired
    ViewConnection viewConnection;
    private final Logger logger = Logger.getLogger(ApplicationStartup.class.getName());

    /**
     * Retrieves all menu module information.
     * @return A list of MenuModuleInfoDto objects.
     */
    public List<MenuModuleInfoDto> getAllMenuModuleInfoDto() {
        ResultSet resultSet = viewConnection.getConnectionWithResultSet("select role_id, parent_module_id, parent_module_name, menu_id, menu_name,  " +
                "menu_link_name, login_id,login_name,module_ID, module_NAME,  " +
                "COMPANY_MARKER, COMPANY_NAME,NGS, menu_report_heading,   " +
                "report_pdf, report_xls, report_txt,finyear_from, finyear_to   " +
                "from vw_DCST_security_menu order by module_id, menu_id");
        List<MenuModuleInfoDto> result = new ArrayList<>();
        try {
            result = MenuModuleTransformer.ResutltSetToMenuModuleInfoDto(resultSet);
        } catch (SQLException exception) {
            logger.info(exception.getMessage());
        } finally {
            try {
                resultSet.close();
                resultSet = null;
                viewConnection.freeConnection();
            } catch (SQLException exception) {
                logger.info(exception.getMessage());
            }
        }
        return result;
    }
}
