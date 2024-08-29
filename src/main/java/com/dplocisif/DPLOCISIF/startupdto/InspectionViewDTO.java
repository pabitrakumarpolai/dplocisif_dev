package com.dplocisif.DPLOCISIF.startupdto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "vw_dcst_item_inspec")
public class InspectionViewDTO {
    @Id
    @Column(name = "inspection_no")
    private String inspectionNo;

    @Column(name = "inspection_date")
    @Temporal(TemporalType.DATE)
    private Date inspectionDate;

    @Column(name = "challan_no")
    private String challanNo;

    @Column(name = "challan_srl_no")
    private String challanSrlNo;

    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "qty_accepted")
    private Long qtyAccepted;

    @Column(name = "qty_rejected")
    private Long qtyRejected;

    @Column(name = "discrepancy_noted")
    private String discrepancyNoted;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "depo_code")
    private String depoCode;

    @Column(name = "dept_challan_no")
    private String deptChallanNo;

    @Column(name = "rsl_no")
    private String rslNo;

    @Column(name = "challan_date")
    @Temporal(TemporalType.DATE)
    private Date challanDate;

    @Column(name = "po_no")
    private String poNo;

    @Column(name = "po_date")
    @Temporal(TemporalType.DATE)
    private Date poDate;

    @Column(name = "vendor_code")
    private String vendorCode;

    @Column(name = "vendor_name")
    private String vendorName;

    @Column(name = "transaction_code")
    private String transactionCode;

    @Column(name = "trans_description")
    private String transDescription;

    @Column(name = "job_code")
    private String jobCode;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "ord_itmsrl_no")
    private String ordItmSrlNo;

    @Column(name = "challan_qty_in_number")
    private Long challanQtyInNumber;

    @Column(name = "folio_no")
    private String folioNo;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "unit_code")
    private String unitCode;

    @Column(name = "unit_description")
    private String unitDescription;
}
