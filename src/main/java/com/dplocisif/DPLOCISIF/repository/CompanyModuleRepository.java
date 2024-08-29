package com.dplocisif.DPLOCISIF.repository;

import com.dplocisif.DPLOCISIF.startupdto.CompanyModuleInfoDTO;

import java.sql.SQLException;
import java.util.List;

public interface CompanyModuleRepository{
    List<CompanyModuleInfoDTO> getDcpyCompanyDetails() throws SQLException;
}
