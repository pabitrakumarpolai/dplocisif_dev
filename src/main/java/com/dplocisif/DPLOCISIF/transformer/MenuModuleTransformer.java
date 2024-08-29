package com.dplocisif.DPLOCISIF.transformer;


import com.dplocisif.DPLOCISIF.compositeKeys.DcpyViewSecurityMenyKey;
import com.dplocisif.DPLOCISIF.model.MenuModule;
import com.dplocisif.DPLOCISIF.model.MenuUserAccessModule;
import com.dplocisif.DPLOCISIF.startupdto.MenuModuleInfoDto;
import com.dplocisif.DPLOCISIF.startupdto.MenuRoleModuleDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuModuleTransformer {
    public static MenuUserAccessModule menuRoleModuleToMenuUserAccessModule(MenuRoleModuleDTO menuModule, Long loginId) {
        return MenuUserAccessModule.builder()
                .menuId(Long.parseLong(menuModule.getMenuId()))
                .moduleId(Long.parseLong(menuModule.getModuleId()))
                .includeExclude("E")
                .loginId(loginId)
                .build();
    }
    public static List<MenuModuleInfoDto> ResutltSetToMenuModuleInfoDto(ResultSet rs) throws SQLException {
        ArrayList<MenuModuleInfoDto> list = new ArrayList();
        while (rs.next()) {
            DcpyViewSecurityMenyKey dcpyViewSecurityMenyKey = new DcpyViewSecurityMenyKey();
            dcpyViewSecurityMenyKey.setRoleId(rs.getLong("ROLE_ID"));
            dcpyViewSecurityMenyKey.setMenuId(rs.getLong("MENU_ID"));
            dcpyViewSecurityMenyKey.setModuleId(rs.getLong("MODULE_ID"));

            MenuModuleInfoDto objMenuVO = new MenuModuleInfoDto();
            objMenuVO.setDcpyViewSecurityMenyKey(dcpyViewSecurityMenyKey);
            objMenuVO.setLoginId(rs.getLong("LOGIN_ID"));
            objMenuVO.setLoginName(rs.getString("LOGIN_NAME"));
            objMenuVO.setMenuName(rs.getString("MENU_NAME"));
            objMenuVO.setMenuLinkName(rs.getString("MENU_LINK_NAME"));
            objMenuVO.setModuleName(rs.getString("MODULE_NAME"));
            objMenuVO.setCompanyMarker(rs.getString("COMPANY_MARKER"));
            objMenuVO.setCompanyName(rs.getString("COMPANY_NAME"));
            objMenuVO.setNgs(rs.getString("NGS"));
            objMenuVO.setMenuReportHeading(rs.getString("menu_report_heading"));
            objMenuVO.setReportPdf(rs.getString("report_pdf"));
            objMenuVO.setReportXls(rs.getString("report_xls"));
            objMenuVO.setReportTxt(rs.getString("report_txt"));
            objMenuVO.setFinYearFrom(rs.getInt("FINYEAR_FROM"));
            objMenuVO.setFinYearTo(rs.getInt("FINYEAR_TO"));
            objMenuVO.setParentModuleId(rs.getLong("PARENT_MODULE_ID"));
            objMenuVO.setParentModuleName(rs.getString("PARENT_MODULE_NAME"));
            list.add(objMenuVO);
        }
        return list;
    }
}
