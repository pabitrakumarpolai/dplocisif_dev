package com.dplocisif.DPLOCISIF.repository;

import com.dplocisif.DPLOCISIF.model.DepoJobModule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepoWiseJobRepository extends JpaRepository<DepoJobModule,Long> {
    @Query(value = "SELECT * FROM T_DCST_M_DEPOWISE_JOB WHERE depo_code = ?1 AND job_code = ?2", nativeQuery = true)
     DepoJobModule findByDepoCodeAndJobCode(String depoCode, Long jobCode);


    @Query("SELECT j FROM DepoJobModule j WHERE " +
            "(:depoCode IS NULL OR j.depoCode LIKE CONCAT('%',:depoCode, '%')) AND " +
            "(:jobCode IS NULL OR FUNCTION('TO_CHAR', j.jobCode) LIKE CONCAT('%',:jobCode, '%')) AND " +
            "(:jobDescription IS NULL OR j.jobDescription LIKE CONCAT('%',:jobDescription, '%'))")
    List<DepoJobModule> searchJobs(@Param("depoCode") String depoCode,
                                 @Param("jobCode") Long jobCode,
                                 @Param("jobDescription") String jobDescription);

    @Query(value = "SELECT * FROM T_DCST_M_DEPOWISE_JOB WHERE depo_code = ?1 AND job_description = ?2", nativeQuery = true)
    List<DepoJobModule> findByDepoCodeAndJobDescription(String depoCode, String jobDescription);

}
