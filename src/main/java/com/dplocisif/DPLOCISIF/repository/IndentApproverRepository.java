package com.dplocisif.DPLOCISIF.repository;

import com.dplocisif.DPLOCISIF.model.IndentApprover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IndentApproverRepository extends JpaRepository<IndentApprover,Long> {

//@Query(value = "SELECT u.LOGIN_ID AS loginId, e.NAM AS nam, u.NGS AS ngs " +
//        "FROM t_dcpy_user_login u " +
//        "JOIN t_dcpy_emp_pay_dtls e ON u.NGS = e.NGS " +
//        "WHERE (:loginId IS NULL OR u.LOGIN_ID = :loginId) ", nativeQuery = true)

    @Query(value = "SELECT u.LOGIN_ID AS loginId, u.NGS AS ngs, e.NAM AS nam " +
            "FROM t_dcpy_user_login u " +
            "JOIN (SELECT DISTINCT NGS, NAM FROM t_dcpy_emp_pay_dtls) e ON u.NGS = e.NGS " +
            "WHERE (:loginId IS NULL OR u.LOGIN_ID = :loginId)", nativeQuery = true)
            List<Object[]> findByLoginId(Long loginId);





    @Query(value = "SELECT * FROM T_DCST_M_APPROVER_LIST WHERE DOCUMENT_TYPE = ?1 AND DEPARTMENT = ?2", nativeQuery = true)
    List<IndentApprover> findByDocumentTypeAndDepartment(String documentType, long department);


    @Query(value = "SELECT i FROM IndentApprover i WHERE " +
            "(:department IS NULL OR FUNCTION('TO_CHAR', i.department) LIKE CONCAT('%', :department, '%')) AND " +
            "(:documentType IS NULL OR :documentType = '' OR i.documentType LIKE CONCAT('%', :documentType, '%'))")
    List<IndentApprover> findByIndent(String documentType, Long department);
}
