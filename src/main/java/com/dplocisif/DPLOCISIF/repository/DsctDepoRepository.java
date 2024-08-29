package com.dplocisif.DPLOCISIF.repository;

import com.dplocisif.DPLOCISIF.model.DsctDepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DsctDepoRepository extends JpaRepository<DsctDepo, Long> {
    @Query(nativeQuery = true, value = "select DEPO_CODE from T_DCST_M_DEPO")
    List<String> findAllDepoCode();

    @Query("SELECT d FROM DsctDepo d WHERE " +
            "(d.depoCode = :depoCode OR d.depoDescription = :depoDescription)")
    List<DsctDepo> findAllDepoCodeAndDepoDescription(@Param("depoCode")String depoCode, @Param("depoDescription") String depoDescription);

    @Query("SELECT d FROM DsctDepo d WHERE " +
            "(:depoCode IS NULL OR :depoCode = '' OR d.depoCode LIKE CONCAT('%', :depoCode, '%')) AND " +
            "(:depoDescription IS NULL OR :depoDescription = '' OR d.depoDescription LIKE CONCAT('%', :depoDescription, '%'))")
    List<DsctDepo> findDepo(
            @Param("depoCode") String depoCode,
            @Param("depoDescription") String depoDescription);

    Optional<DsctDepo> findByDepoCode(String depoCode);

}
