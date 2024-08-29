package com.dplocisif.DPLOCISIF.model;


import com.dplocisif.DPLOCISIF.compositeKeys.DcpyEmpPayCompositeKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(DcpyEmpPayCompositeKey.class)
@Table(name = "t_dcpy_emp_pay_dtls")
public class EmployeePayDetailsModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NGS")
    private String ngs;

    @Column(name = "NDS")
    private Long nds;

    @Id
    @Column(name = "SAL_MONTH")
    private Long salMonth;

    @Id
    @Column(name = "SAL_YEAR")
    private Long salYear;

    @Id
    @Column(name = "COMPANY_MARKER")
    private Long companyMarker;

    @Column(name = "NCCR")
    private Long nccr;

    @Column(name = "NPOST")
    private Long npost;

    @Column(name = "NAM")
    private String nam;

    @Column(name = "LPM")
    private Long lpm;

    @Column(name = "LPY")
    private Long lpy;

    @Column(name = "SEX")
    private String sex;

    @Column(name = "MS")
    private String ms;

    @Column(name = "NC")
    private Long nc;

    @Column(name = "AC_TYPE")
    private String acType;

    @Column(name = "AC_NO")
    private Long acNo;

    @Column(name = "BDATE")
    private Date bdate;

    @Column(name = "JDATE")
    private Date jdate;

    @Column(name = "BSALARY")
    private Long bsalary;

    @Column(name = "PFM")
    private Long pfm;

    @Column(name = "OTM")
    private Long otm;

    @Column(name = "ABM")
    private Long abm;

    @Column(name = "CON_RE_M")
    private Long conReM;

    @Column(name = "RAMNT")
    private Long ramnt;

    @Column(name = "RMNTH")
    private Long rmnth;

    @Column(name = "RYEAR")
    private Long ryear;

    @Column(name = "CAR_CON_ALLOW")
    private Long carConAllow;

    @Column(name = "ENT_CAN_SHFT_ALLOW")
    private Long entCanShftAllow;

    @Column(name = "CAN_SHFT")
    private Long canShft;

    @Column(name = "TXPERQ")
    private Long txperq;

    @Column(name = "FIX_SPAY")
    private Long fixSpay;

    @Column(name = "BONUS_EXGRA")
    private Long bonusExgra;

    @Column(name = "GFL")
    private Long gfl;

    @Column(name = "GLDATE")
    private Date gldate;

    @Column(name = "INGF")
    private Long ingf;

    @Column(name = "PFL")
    private Long pfl;

    @Column(name = "PLDATE")
    private Date pldate;

    @Column(name = "INPF")
    private Long inpf;

    @Column(name = "SFL")
    private Long sfl;

    @Column(name = "SLDATE")
    private Date sldate;

    @Column(name = "INSF")
    private Long insf;

    @Column(name = "CONST_ALL_MARK")
    private Long constAllMark;

    @Column(name = "ELD")
    private Long eld;

    @Column(name = "MLD")
    private Long mld;

    @Column(name = "HR_CAR_AMT")
    private Long hrCarAmt;

    @Column(name = "BANK_POST")
    private Long bankPost;

    @Column(name = "L_ITX")
    private Long litx;

    @Column(name = "O_INPRE")
    private Long oinpre;

    @Column(name = "DEGN")
    private String degn;

    @Column(name = "COMP_CODE")
    private String compCode;

    @Column(name = "LDEG")
    private Long ldeg;

    @Column(name = "LYEAR")
    private Long lyear;

    @Column(name = "HDEG")
    private Long hdeg;

    @Column(name = "HYEAR")
    private Long hyear;

    @Column(name = "VPF")
    private Long vpf;

    @Column(name = "SHIFT_MARKER")
    private Long shiftMarker;

    @Column(name = "CU_ITX")
    private Long cuItx;

    @Column(name = "CU_CPALL")
    private Long cuCpall;

    @Column(name = "FILLER_CU_CDS1097")
    private Long fillerCuCds1097;

    @Column(name = "CU_HR_CAR")
    private Long cuHrCar;

    @Column(name = "ISO_ALFA")
    private String isoAlfa;

    @Column(name = "CU_OTPAY")
    private Long cuOtpay;

    @Column(name = "CU_OREIM")
    private Long cuOreim;

    @Column(name = "CU_SPAY")
    private Long cuSpay;

    @Column(name = "CCA_MARKER")
    private String ccaMarker;

    @Column(name = "CLD")
    private Long cld;

    @Column(name = "INPRE_SS")
    private Long inpreSs;

    @Column(name = "AN_TPAY")
    private Long anTpay;

    @Column(name = "CU_BSALARY")
    private Long cuBsalary;

    @Column(name = "CU_HR")
    private Long cuHr;

    @Column(name = "CU_GRPAY")
    private Long cuGrpay;

    @Column(name = "CU_TPAY")
    private Long cuTpay;

    @Column(name = "CU_PF")
    private Long cuPf;

    @Column(name = "CU_INPRE_SS")
    private Long cuInpreSs;

    @Column(name = "CU_FPF")
    private Long cuFpf;

    @Column(name = "NO_DAYS")
    private Long noDays;

    @Column(name = "L")
    private Long l;

    @Column(name = "Y")
    private Long y;

    @Column(name = "CL")
    private Long cl;

    @Column(name = "EL")
    private Long el;

    @Column(name = "ML")
    private Long ml;

    @Column(name = "SP")
    private Long sp;

    @Column(name = "TR")
    private Long tr;

    @Column(name = "LOH")
    private Long loh;

    @Column(name = "ST")
    private Long st;

    @Column(name = "OT")
    private Long ot;

    @Column(name = "OTS")
    private Long ots;

    @Column(name = "TC_OK_M")
    private Long tcOkM;

    @Column(name = "OT_LD_M")
    private Long otLdM;

    @Column(name = "REIM_M")
    private Long reimM;

    @Column(name = "ADV_SAL")
    private Long advSal;

    @Column(name = "ADV_TRIP")
    private Long advTrip;

    @Column(name = "ADV_PURCH")
    private Long advPurch;

    @Column(name = "BONUS_EXGRA_TYPE")
    private String bonusExgraType;

    @Column(name = "MBASE_MARKER")
    private Long mbaseMarker;

    @Column(name = "MBASE_ALL_MTH")
    private Long mbaseAllMth;

    @Column(name = "MBASE_ALL_CUM")
    private Long mbaseAllCum;

    @Column(name = "HDFC_INST")
    private Long hdfcInst;

    @Column(name = "GRPAY")
    private Long grpay;

    @Column(name = "NETPAY")
    private Long netpay;

    @Column(name = "M_BSALARY")
    private Long mBsalary;

    @Column(name = "HR")
    private Long hr;

    @Column(name = "PFD")
    private Long pfd;

    @Column(name = "INPRE_AMT")
    private Long inpreAmt;

    @Column(name = "RESPON_ALLOW")
    private Long responAllow;

    @Column(name = "FPF_AMT")
    private Long fpfAmt;

    @Column(name = "FPF_MARKER")
    private Long fpfMarker;

    @Column(name = "CONST_ALL_DAYS")
    private Long constAllDays;

    @Column(name = "CPALL_MARK")
    private Long cpallMark;

    @Column(name = "SPAY")
    private Long spay;

    @Column(name = "REIM_RESPON")
    private Long reimRespon;

    @Column(name = "OTPAY")
    private Long otpay;

    @Column(name = "REIM")
    private Long reim;

    @Column(name = "CND")
    private Long cnd;

    @Column(name = "ITD")
    private Long itd;

    @Column(name = "CPALL_MTH")
    private Long cpallMth;

    @Column(name = "FILLER_CDS1097")
    private Long fillerCds1097;

    @Column(name = "MINGF")
    private Long mingf;

    @Column(name = "MINPF")
    private Long minpf;

    @Column(name = "MINSF")
    private Long minsf;

    @Column(name = "HR_CAR_DEDUC")
    private Long hrCarDeduc;

    @Column(name = "GOVT_BOND")
    private Long govtBond;

    @Column(name = "PART_SAL")
    private Long partSal;

    @Column(name = "CCA_CUM")
    private Long ccaCum;

    @Column(name = "CCA_MONTHLY")
    private Long ccaMonthly;

    @Column(name = "TAX_EXEMP_RECT")
    private Long taxExempRect;

    @Column(name = "HDFC_CUM")
    private Long hdfcCum;

    @Column(name = "REIM_TEMP_MARK")
    private Long reimTempMark;

    @Column(name = "WB_TAX_MRK")
    private Long wbTaxMrk;

    @Column(name = "ADL_CONST_MARK")
    private Long adlConstMark;

    @Column(name = "ADL_CONST_ALL")
    private Long adlConstAll;

    @Column(name = "ADDL_REIM")
    private Long addlReim;

    @Column(name = "D_RETRO")
    private Long dRetro;

    @Column(name = "REF_LOAN_PF")
    private Long refLoanPf;

    @Column(name = "NREF_LOAN_PF")
    private Long nrefLoanPf;

    @Column(name = "PFL_NR")
    private Long pflNr;

    @Column(name = "MNYR_NR")
    private Long mnyrNr;

    @Column(name = "DT_NR")
    private Long dtNr;

    @Column(name = "SUS_AMT")
    private Long susAmt;

    @Column(name = "SUS_INST")
    private Long susInst;

    @Column(name = "ADV_PUR_AMT")
    private Long advPurAmt;

    @Column(name = "ADV_PUR_INST")
    private Long advPurInst;

    @Column(name = "ADV_TR_AMT")
    private Long advTrAmt;

    @Column(name = "ADV_TR_INST")
    private Long advTrInst;

    @Column(name = "DRIVER")
    private Long driver;

    @Column(name = "CU_ADL_CCA")
    private Long cuAdlCca;

    @Column(name = "CONST_ALL_MNTH")
    private Long constAllMnth;

    @Column(name = "CONST_ALL_CUM")
    private Long constAllCum;

    @Column(name = "ADL_CONST_ALL_MNTH")
    private Long adlConstAllMnth;

    @Column(name = "ADL_CONST_ALL_CUM")
    private Long adlConstAllCum;

    @Column(name = "HRA_PERCENT")
    private Long hraPercent;

    @Column(name = "C_RETRO")
    private Long cRetro;

    @Column(name = "BANK_MARK")
    private Long bankMark;

    @Column(name = "LEFT_YEAR")
    private Long leftYear;

    @Column(name = "LEFT_MON")
    private Long leftMon;

    @Column(name = "MONTHLY_PENSION")
    private Long monthlyPension;

    @Column(name = "CU_PENSION")
    private Long cuPension;

    @Column(name = "GRADE_ALL_MNTH")
    private Long gradeAllMnth;

    @Column(name = "GRADE_ALL_CUM")
    private Long gradeAllCum;

    @Column(name = "REBET_CU_PF")
    private Long rebetCuPf;

    @Column(name = "SUP_ALL_MNTH")
    private Long supAllMnth;

    @Column(name = "SUP_ALL_CUM")
    private Long supAllCum;

    @Column(name = "SHIFT_BASIC")
    private Long shiftBasic;

    @Column(name = "INST_ALL")
    private Long instAll;

    @Column(name = "INST_MNTH")
    private Long instMnth;

    @Column(name = "INST_CUM")
    private Long instCum;

    @Column(name = "CONV_MNTH")
    private Long convMnth;

    @Column(name = "CONV_CUM")
    private Long convCum;

    @Column(name = "CONV_MNTH_TAX")
    private Long convMnthTax;

    @Column(name = "CONV_CUM_TAX")
    private Long convCumTax;

    @Column(name = "WB_TAX_MNTH")
    private Long wbTaxMnth;

    @Column(name = "WB_TAX_CUM")
    private Long wbTaxCum;

    @Column(name = "TAXYEAR")
    private Long taxyear;

    @Column(name = "PF_EMPL_CONT")
    private Long pfEmplCont;

    @Column(name = "SATOT_RATE")
    private Long satotRate;

    @Column(name = "SATOT_AMOUNT")
    private Long satotAmount;

    @Column(name = "SHIFT_ALW")
    private Long shiftAlw;

    @Column(name = "CAD_ALW")
    private Long cadAlw;

    @Column(name = "SPL_ALW_TYPE")
    private String splAlwType;

    @Column(name = "SPL_ALW_VALUE")
    private Long splAlwValue;

    @Column(name = "M_CAR_CON_ALLOW")
    private Long mCarConAllow;

    @Column(name = "M_ENTR_ALLOW")
    private Long mEntrAllow;

    @Column(name = "M_REIM_RESPON")
    private Long mReimRespon;

    @Column(name = "M_DRIVER")
    private Long mDriver;

    @Column(name = "M_CAN_SHFT")
    private Long mCanShft;

    @Column(name = "M_DON_AMOUNT")
    private Long mDonAmount;

    @Column(name = "REGULAR_OR_CONTRACT")
    private String regularOrContract;

    @Column(name = "SP_ALW_MNTH")
    private Long spAlwMnth;

    @Column(name = "PAYROLL_DONE_ON")
    private Date payrollDoneOn;

    @Column(name = "DAILY_WAGE_UNIT")
    private Long dailyWageUnit;

    @Column(name = "ESI_FLAG")
    private Long esiFlag;

    @Column(name = "M_ESI_AMT_OWN")
    private Long mEsiAmtOwn;

    @Column(name = "M_ESI_AMT_COMPANY")
    private Long mEsiAmtCompany;

    @Column(name = "DA_PERCENT")
    private Long daPercent;

    @Column(name = "FIX_DA")
    private Long fixDa;

    @Column(name = "M_DA_AMT")
    private Long mDaAmt;

    @Column(name = "M_GF_INTEREST_AMT")
    private Long mGfInterestAmt;

    @Column(name = "GFL_INTEREST_RATE")
    private Long gflInterestRate;

    @Column(name = "PAY_SCALE_SLAB_ID")
    private String payScaleSlabId;

    @Column(name = "CONFIRMED_DATE")
    private Date confirmedDate;

    @Column(name = "DOL_COMPANY")
    private Date dolCompany;

    @Column(name = "DOL_REASON")
    private String dolReason;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "REPORTING_NGS")
    private String reportingNgs;

    @Column(name = "EMP_TYPE_ID")
    private String empTypeId;

    @Column(name = "SL_FULL_PAY")
    private Long slFullPay;

    @Column(name = "SL_HALF_PAY")
    private Long slHalfPay;

    @Column(name = "SL_DUE")
    private Long slDue;

    @Column(name = "SL_OVER_FLOW")
    private Long slOverFlow;

    @Column(name = "EL_OVER_FLOW")
    private Long elOverFlow;

    @Column(name = "ML_OVER_FLOW")
    private Long mlOverFlow;

    @Column(name = "NEXTINCR")
    private String nextincr;

    @Column(name = "QUARTER_NO")
    private String quarterNo;

    @Column(name = "ACC")
    private String acc;

    @Column(name = "BANKER")
    private String banker;

    @Column(name = "WP_HR")
    private Long wpHr;

    @Column(name = "WP_MNT")
    private Long wpMnt;

    @Column(name = "LASTMTRREAD")
    private Long lastmtrread;

    @Column(name = "CURRENTMTRREAD")
    private Long currentmtrread;

    @Column(name = "CONSUMEDMTR")
    private Long consumedmtr;

    @Column(name = "QTRRENT")
    private Long qtrrent;

    @Column(name = "CU_QTRRENT")
    private Long cuQtrrent;

    @Column(name = "WATERPERQ")
    private Long waterperq;

    @Column(name = "CU_WATERPERQ")
    private Long cuWaterperq;

    @Column(name = "EMPSHRTNM")
    private String empshrtnm;

    @Column(name = "IS_HANDICAP")
    private String isHandicap;

    @Column(name = "RETIREMENT_DATE")
    private Date retirementDate;

    @Column(name = "SPOUSE_WORKING")
    private String spouseWorking;

    @Column(name = "IS_NURSE")
    private String isNurse;

    @Column(name = "PERSONNEL_ORDER_NO")
    private String personnelOrderNo;

    @Column(name = "PERSONNEL_ORDER_DATE")
    private Date personnelOrderDate;

    @Column(name = "SPOUSE_NGS")
    private String spouseNgs;

    @Column(name = "FAMILY_HRA_LIMIT")
    private Long familyHraLimit;
}
