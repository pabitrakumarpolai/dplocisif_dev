package com.dplocisif.DPLOCISIF.repository;

import com.dplocisif.DPLOCISIF.model.UserLoginModule;
import com.dplocisif.DPLOCISIF.startupdto.UserLoginNameDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserLoginModuleRepository extends JpaRepository<UserLoginModule, Long> {

    @Query(value = "SELECT u.LOGIN_ID AS loginId,  u.NGS AS ngs, e.NAM AS nam " +
            "FROM t_dcpy_user_login u " +
            "JOIN t_dcpy_emp_pay_dtls e ON u.NGS = e.NGS " +
            "GROUP BY u.LOGIN_ID, u.NGS, e.NAM " +
            "ORDER BY u.LOGIN_ID ASC", nativeQuery = true)
    List<Object[]> findByLoginIdAndNam(PageRequest pageable);

}
