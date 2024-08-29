package com.dplocisif.DPLOCISIF.repository.view;

import com.dplocisif.DPLOCISIF.startupdto.InspectionViewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemInspectionViewRepository extends JpaRepository<InspectionViewDTO, String>, JpaSpecificationExecutor<InspectionViewDTO> {
}
