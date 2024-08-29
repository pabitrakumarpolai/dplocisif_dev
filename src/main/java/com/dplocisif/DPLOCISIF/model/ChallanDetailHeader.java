package com.dplocisif.DPLOCISIF.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "T_DCST_TH_CHALLAN")
public class ChallanDetailHeader {
    @Id
    @Column(name = "CHALLAN_NO")
    private String challanNo;

    @Column(name = "DATE_OF_RECEIPT")
    private Date dateOfReceipt;

    @Column(name = "RSL_NO")
    private String rslNo;

    @Column(name = "DEPT_CHALLAN_NO")
    private String deptChallanNo;

    @Column(name = "CHALLAN_DATE")
    private Date challanDate;

    @Column(name = "DEPO_CODE")
    private String depoCode;

    @Column(name = "TRANSACTION_CODE")
    private Long transactionCode;

    @Column(name = "PO_NO")
    private String poNo;

    @Column(name = "PO_DATE")
    private Date poDate;

    @Column(name = "VENDOR_CODE")
    private String vendorCode;

    @Column(name = "CARRIER_REF")
    private String carrierRef;

    @Column(name = "VEHICLE_NO")
    private String vehicleNo;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "CHALLAN_STATUS")
    private String challanStatus;

    @Column(name = "REJECT_SUPPLY_FLAG")
    private String rejectSupplyFlag;

    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy;

    @Column(name = "CREATED_ON", nullable = false)
    private Date createdOn;

    @Column(name = "MODIFIED_BY")
    private Long modifiedBy;

    @Column(name = "MODIFIED_ON")
    private Date modifiedOn;

    @OneToMany(mappedBy = "challanDetailHeader", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ChallanDetailChild> challanDetails;
}
