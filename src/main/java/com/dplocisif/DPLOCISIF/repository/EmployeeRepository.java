package com.dplocisif.DPLOCISIF.repository;

import com.dplocisif.DPLOCISIF.model.EmployeeAccountDetailsModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeAccountDetailsModule, Long> {
    @Query(value = "SELECT " +
            "l.COMPANY_MARKER AS COMPANY_MARKER, " +
            "l.LOGIN_ID AS LOGIN_ID, " +
            "l.ROLE_ID AS ROLE_ID, " +
            "l.LOGIN_NAME AS LOGIN_NAME, " +
            "get_udc_fn(l.PASSWORD) AS PASSWORD, " +
            "l.NGS AS NGS, " +
            "dcpy_new_emp_pg.get_emp_login_nm(l.company_marker, l.ngs) AS emp_name, " +
            "com.company_name AS company_name " +
            "FROM " +
            "T_DCST_USER_LOGIN l, " +
            "T_DCPY_COMPANY com " +
            "WHERE " +
            "UPPER(l.login_name) = UPPER(:loginName) " +
            "AND get_udc_fn(l.password) = :password " +
            "AND com.company_marker = l.company_marker", nativeQuery = true)
    List<Object[]> findUserByLoginNameAndPassword(@Param("loginName") String loginName, @Param("password") String password);
}
