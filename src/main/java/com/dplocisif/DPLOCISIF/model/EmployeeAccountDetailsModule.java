package com.dplocisif.DPLOCISIF.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_dcpy_emp_acc_dtls")
public class EmployeeAccountDetailsModule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "NGS")
    private String id;
    @Column(name = "PAN_NUMBER")
    private String panNumber;
    @Column(name = "HDFC_AC_NO")
    private String hdfcAcNo;
    @Column(name = "BANK_AC_NO")
    private String bankAcNo;
    @Column(name = "KC_ESI_NO")
    private String kcEsiNo;
    @Column(name = "KC_ESI_DOC_CODE")
    private String kcEsiDocCode;
    @Column(name = "KC_ESI_DOC")
    private String kcEsiDoc;
    @Column(name = "KC_ESI_DOC_ADD")
    private String kcEsiDocAdd;
    @Column(name = "ESI_DOCNO")
    private String esiDocNo;
    @Column(name = "FPFNO")
    private String fpfNo;
    @Column(name = "CSSNO")
    private String ccsNo;
    @Column(name = "MCSNO")
    private String mcsNo;
    @Column(name = "PFNO")
    private String pfNo;
    @Column(name = "AADHAR_NO")
    private String aadharNo;
    @Column(name = "UAN_NO")
    private String uanNo;
    @Column(name = "BANK_BRANCH")
    private String bankBranch;
    @Column(name = "IFSC_CODE")
    private String ifscCode;
    @Column(name = "BLOOD_GROUP")
    private String bloodGroup;
    @Column(name = "EMP_PHOTO")
    private byte[] empPhoto;
    @Column(name = "DB_NO")
    private String dbNo;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "COMPANY_MARKER", insertable=false, updatable=false)
    CompanyModule companyModule;
}
