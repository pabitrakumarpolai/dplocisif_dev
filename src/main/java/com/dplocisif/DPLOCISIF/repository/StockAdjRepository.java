package com.dplocisif.DPLOCISIF.repository;

import com.dplocisif.DPLOCISIF.model.StockAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

public interface StockAdjRepository extends JpaRepository<StockAdjustment,Long> {
    @Query(nativeQuery = true, value = "SELECT dcst_utility_pg.SERIAL_NO_GEN_FN(:tableName, :colName) FROM dual")
    Long callGenSerialNo( @Param("tableName") String tableName, @Param("colName") String colName);
}
