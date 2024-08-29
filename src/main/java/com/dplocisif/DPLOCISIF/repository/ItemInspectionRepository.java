package com.dplocisif.DPLOCISIF.repository;

import com.dplocisif.DPLOCISIF.model.ItemInspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

public interface ItemInspectionRepository extends JpaRepository<ItemInspection, String> {
    @Query(nativeQuery = true, value = "SELECT dcst_utility_pg.PRIMARY_KEY_GEN_FN(:prefix, :tabName, :colName, :srlNoLength, :date) FROM dual")
    String callGenPrimaryKey(@Param("prefix") String prefix , @Param("tabName") String tabName, @Param("colName") String colName, @Param("srlNoLength") long serialNoLength, @Param("date")Date date);
}
