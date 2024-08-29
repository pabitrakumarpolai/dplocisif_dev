package com.dplocisif.DPLOCISIF.repository;

import com.dplocisif.DPLOCISIF.model.IndentInstructionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IndentInstructionInfoRepository extends JpaRepository<IndentInstructionInfo, Long>
{
    Optional<IndentInstructionInfo> findBySrlNo(Long srlNo);


    @Query("SELECT i FROM IndentInstructionInfo i WHERE " +
            "(:srlNo IS NULL OR :srlNo = 0 OR CAST(i.srlNo AS string) LIKE CONCAT('%', :srlNo, '%')) AND " +
            "(:instructDesc IS NULL OR :instructDesc = '' OR i.instructDesc LIKE CONCAT('%', :instructDesc, '%'))")
    List<IndentInstructionInfo> findByIndentInstruct(@Param("srlNo") Long srlNo, @Param("instructDesc") String instructDesc);

    }

