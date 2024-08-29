package com.dplocisif.DPLOCISIF.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_dcpy_company")
@NamedStoredProcedureQuery(
        name = "getDcpyCompany",
        procedureName = "DCPY_UTILITY_PG.GET_DCPY_COMPANY_PR",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Void.class, name = "p_RefCur_dcpy_company")
        }
)
public class CompanyModule {
    @Id
    @Column(name = "COMPANY_MARKER", precision = 30)
    private Long companyMarker;

    @Column(name = "COMPANY_CODE")
    private String companyCode;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "COMPANY_ADDRESS")
    private String companyAddress;

    @Column(name = "LAST_PAY_MONTH")
    private Long lastPayMonth;

    @Column(name = "LAST_PAY_YEAR")
    private Long lastPayYear;

    @Column(name = "COMPANY_PAN_NO")
    private String companyPanNo;

    @Column(name = "COMPANY_TAN_NO")
    private String companyTanNo;

    @Column(name = "CIRCLE_ADDRESS")
    private String circleAddress;

    @Column(name = "ACCOUNTANT_NAME")
    private String accountantName;

    @Column(name = "ACCOUNTANT_FATHER_NAME")
    private String accountantFatherName;

    @Column(name = "ACCOUNTANT_DESIGNATION")
    private String accountantDesignation;

    @Column(name = "ETDS_DEDUCTOR_NAME")
    private String etdsDeductorName;

    @Column(name = "ETDS_DEDUCTOR_ADDR1")
    private String etdsDeductorAddr1;

    @Column(name = "ETDS_DEDUCTOR_ADDR2")
    private String etdsDeductorAddr2;

    @Column(name = "ETDS_DEDUCTOR_ADDR3")
    private String etdsDeductorAddr3;

    @Column(name = "ETDS_DEDUCTOR_ADDR4")
    private String etdsDeductorAddr4;

    @Column(name = "ETDS_DEDUCTOR_ADDR5")
    private String etdsDeductorAddr5;

    @Column(name = "ETDS_DEDUCTOR_STATE")
    private Long etdsDeductorState;

    @Column(name = "ETDS_DEDUCTOR_PIN")
    private Long etdsDeductorPin;

    @Column(name = "ETDS_DEDUCTOR_EMAIL")
    private String etdsDeductorEmail;

    @Column(name = "ETDS_PERSON_NAME")
    private String etdsPersonName;

    @Column(name = "ETDS_PERSON_DESIG")
    private String etdsPersonDesig;

    @Column(name = "ETDS_PERSON_ADDR1")
    private String etdsPersonAddr1;

    @Column(name = "ETDS_PERSON_ADDR2")
    private String etdsPersonAddr2;

    @Column(name = "ETDS_PERSON_ADDR3")
    private String etdsPersonAddr3;

    @Column(name = "ETDS_PERSON_ADDR4")
    private String etdsPersonAddr4;

    @Column(name = "ETDS_PERSON_ADDR5")
    private String etdsPersonAddr5;

    @Column(name = "ETDS_PERSON_STATE")
    private Long etdsPersonState;

    @Column(name = "ETDS_PERSON_PIN")
    private Long etdsPersonPin;

    @Column(name = "ETDS_PERSON_EMAIL")
    private String etdsPersonEmail;

    @Column(name = "ETDS_PERSON_MOBILE")
    private Long etdsPersonMobile;

    @Column(name = "ETDS_DEDUCTOR_STDCODE")
    private Long etdsDeductorStdCode;

    @Column(name = "ETDS_DEDUCTOR_TELNO")
    private Long etdsDeductorTelNo;

    @Column(name = "ETDS_PERSON_STDCODE")
    private Long etdsPersonStdCode;

    @Column(name = "ETDS_PERSON_TELNO")
    private Long etdsPersonTelNo;

    @Column(name = "PF_ACCT_PREFIX")
    private String pfAcctPrefix;

    @Column(name = "COMPANY_LOGO")
    private byte[] companyLogo;

    @Column(name = "APPS_ENTITY")
    private String appsEntity;

    @Column(name = "LIC2")
    private String lic2;

    @Column(name = "SST_NO")
    private String sstNo;

    @Column(name = "CST_NO")
    private String cstNo;

    @Column(name = "FINYEAR_FROM")
    private Long finYearFrom;

    @Column(name = "FINYEAR_TO")
    private Long finYearTo;

    @Column(name = "CIN_NO")
    private String cinNo;

    @Column(name = "PYRL_RELEASE_MNTH")
    private Integer pyrlReleaseMnth;

    @Column(name = "PYRL_RELEASE_YR")
    private Integer pyrlReleaseYr;

    @OneToMany(mappedBy = "companyModule", cascade = CascadeType.ALL)
    List<EmployeePfDetailsModule> employeePfDetailsModules;
    @OneToMany(mappedBy = "companyModule", cascade = CascadeType.ALL)
    List<EmployeeAccountDetailsModule> employeeDetailsModules;
}
