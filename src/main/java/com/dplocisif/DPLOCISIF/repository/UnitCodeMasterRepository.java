package com.dplocisif.DPLOCISIF.repository;

import com.dplocisif.DPLOCISIF.model.UnitCodeMasterModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UnitCodeMasterRepository extends JpaRepository<UnitCodeMasterModule,Long> {
    @Query(nativeQuery = true, value = "select UNIT_CODE from T_DCST_M_UNIT_CODE")
    List<String> findAllUnitCode();
}
