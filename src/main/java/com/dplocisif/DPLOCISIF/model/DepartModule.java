package com.dplocisif.DPLOCISIF.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_dcpy_dpl_dept")
public class DepartModule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DEPT_CODE")
    private Long id;
    @Column(name = "DEPT_NAME")
    private String deptName;
    @Column(name = "MA_GRP")
    private Long maGrp;
    @Column(name = "OPRA_FLAG")
    private String opraFlag;
    @Column(name = "STF_OFF")
    private String stfOff;
    @Column(name = "PAY_CODE")
    private Long payCode;
    @Column(name = "BUSI_CODE")
    private Long bustCode;
    @Column(name = "GROUP_CODE")
    private Long groupCode;
    @Column(name = "SUB_GROUP")
    private Long subGroup;
    @Column(name = "ATN_FLAG")
    private Long atnFlag;
}