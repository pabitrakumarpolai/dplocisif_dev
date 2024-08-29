package com.dplocisif.DPLOCISIF.repository.view;

import com.dplocisif.DPLOCISIF.startupdto.StockAdjViewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StockAdjViewRepository extends JpaRepository<StockAdjViewDTO, Long>, JpaSpecificationExecutor<StockAdjViewDTO> {
}
