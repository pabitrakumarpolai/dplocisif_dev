package com.dplocisif.DPLOCISIF.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_DCST_M_UNIT_CODE")
public class UnitCodeMasterModule
{
    @Id
    @Column(name = "UNIT_CODE", nullable = false)
    private Long unitCode;
    @Column(name = "UNIT_DESCRIPTION",length = 100,nullable = false)
    private String unitDescription;
    @Column(name = "CREATED_BY",nullable = false)
    private Long createdBy;
    @Column(name = "CREATED_ON", nullable = false)
    private Date createdOn;
    @Column(name = "MODIFIED_BY")
    private Long modifiedBy;
    @Column(name = "MODIFIED_ON")
    private Date modifiedOn;
}