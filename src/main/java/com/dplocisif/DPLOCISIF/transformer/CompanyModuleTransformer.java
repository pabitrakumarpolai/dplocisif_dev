package com.dplocisif.DPLOCISIF.transformer;

import com.dplocisif.DPLOCISIF.startupdto.CompanyModuleInfoDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyModuleTransformer {

    public static CompanyModuleInfoDTO transformCompany(ResultSet rs) throws SQLException {
        return CompanyModuleInfoDTO.builder()
                .companyMarker(rs.getLong("company_marker"))
                .companyCode(rs.getString("company_code"))
                .companyName(rs.getString("company_name"))
                .companyAddress(rs.getString("company_address"))
                .lastPayMonth(rs.getLong("last_pay_month"))
                .lastPayYear(rs.getLong("last_pay_year"))
                .taxYear(rs.getLong("tax_year"))
                .finyearFrom(rs.getLong("finyear_from"))
                .finyearTo(rs.getLong("finyear_to"))
                .build();
    }
}
