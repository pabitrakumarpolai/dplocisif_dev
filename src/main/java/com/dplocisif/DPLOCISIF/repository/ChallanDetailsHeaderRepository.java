package com.dplocisif.DPLOCISIF.repository;

import com.dplocisif.DPLOCISIF.model.ChallanDetailHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.sql.ResultSet;
import java.sql.Types;

public interface ChallanDetailsHeaderRepository extends JpaRepository<ChallanDetailHeader, String> {
    @Query("SELECT i FROM ChallanDetailHeader i WHERE i.challanStatus='C'")
    Page<ChallanDetailHeader> findAllChallanDetailHeader(Pageable pageRequest);
}
