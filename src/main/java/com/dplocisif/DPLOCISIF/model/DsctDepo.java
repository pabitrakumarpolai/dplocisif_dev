package com.dplocisif.DPLOCISIF.model;

import com.dplocisif.DPLOCISIF.Enum.DepoDepartmentEnum;
import jakarta.persistence.*;
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
@Table(name = "T_DCST_M_DEPO")
public class DsctDepo {
    @Id
    @Column(name = "DEPO_CODE")
    private String depoCode;
    @Column(name = "DEPO_DESCRIPTION")
    private String depoDescription;
    @Column(name = "MODULE_FLAG")
    @Enumerated(EnumType.STRING)
    private DepoDepartmentEnum moduleFlag;
    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy;
    @Column(name = "CREATED_ON", nullable = false)
    private Date createdOn;
    @Column(name = "MODIFIED_BY")
    private Long modifiedBy;
    @Column(name = "MODIFIED_ON")
    private Date modifiedOn;
}
