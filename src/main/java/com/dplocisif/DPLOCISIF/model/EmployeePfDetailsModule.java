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
@Table(name = "t_dcpy_emp_pf_dtls")
public class EmployeePfDetailsModule {
    @Id
    @Column(name = "NGS")
    private String id;

    @Column(name = "PF_YEAR", nullable = false)
    private Long pfYear;

    @Column(name = "PF_WAGE")
    private List<Double> pfWage;

    @Column(name = "PEN_WAGE")
    private List<Double> penWage;

    @Column(name = "PF_EE")
    private List<Double> pfEmployee;

    @Column(name = "PF_ER")
    private List<Double> pfEmployer;

    @Column(name = "PEN_AMT")
    private List<Double> penAmount;

    @Column(name = "NO_DAYS")
    private List<Double> noOfDays;

    @Column(name = "NREF_LOAN")
    private List<Double> nrefLoan;

    @Column(name = "REF_LOAN")
    private List<Double> refLoan;

    @Column(name = "REF_LOAN_INSTALL")
    private List<Double> refLoanInstall;

    @Column(name = "OB_PF_EE")
    private Long obPfEmployee;

    @Column(name = "OB_PF_ER")
    private Long obPfEmployer;

    @Column(name = "OB_FPF_EE")
    private Long obFpfEmployee;

    @Column(name = "OB_FPF_ER")
    private Long obFpfEmployer;

    @Column(name = "OB_PEN")
    private Long obPen;

    @Column(name = "OB_NO_DAYS")
    private Long obNoOfDays;

    @Column(name = "OB_NREF_LOAN")
    private Long obNrefLoan;

    @Column(name = "OB_REF_LOAN")
    private Long obRefLoan;

    @Column(name = "PF_INT_EE")
    private Long pfInterestEmployee;

    @Column(name = "PF_INT_ER")
    private Long pfInterestEmployer;

    @Column(name = "PF_XTRA_INT")
    private Long pfExtraInterest;

    @ManyToOne
    @JoinColumn(name = "COMPANY_MARKER", insertable=false, updatable=false)
    CompanyModule companyModule;
}
