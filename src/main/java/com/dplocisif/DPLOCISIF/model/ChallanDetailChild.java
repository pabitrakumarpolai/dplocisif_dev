package com.dplocisif.DPLOCISIF.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "T_DCST_TD_CHALLAN")
public class ChallanDetailChild {
    @Id
    @Column(name = "CHALLAN_SRL_NO")
    private Long challanSrlNo;

//    @Column(name = "CHALLAN_NO")
//    private String challanNo;

    @Column(name = "ORD_ITMSRL_NO")
    private Long ordItmsrlNo;

    @Column(name = "JOB_CODE")
    private Long jobCode;

    @Column(name = "ITEM_CODE")
    private String itemCode;

    @Column(name = "FOLIO_NO")
    private String folioNo;

    @Column(name = "ITEM_DESCRIPTION")
    private String itemDescription;

    @Column(name = "CHALLAN_QTY_IN_NUMBER")
    private Long challanQtyInNumber;

    @Column(name = "REJECTED_ITEM_SUPPLY")
    private String rejectedItemSupply;

    @Column(name = "UNIT_CODE")
    private Long unitCode;

    @Column(name = "DISCREPANCY_NOTED")
    private String discrepancyNoted;

    @Column(name = "QTY_FINALLY_ACCEPTED")
    private Long qtyFinallyAccepted;

    @Column(name = "INSPECTION_NO")
    private String inspectionNo;

    @Column(name = "INSPECTION_DATE")
    private Date inspectionDate;

    @Column(name = "GRN_NO")
    private String grnNo;

    @Column(name = "GRN_DATE")
    private Date grnDate;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "SUPPLY_NO")
    private String supplyNo;

    @Column(name = "SUPPLY_DATE")
    private Date supplyDate;

    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy;

    @Column(name = "CREATED_ON", nullable = false)
    private Date createdOn;

    @Column(name = "MODIFIED_BY")
    private Long modifiedBy;

    @Column(name = "MODIFIED_ON")
    private Date modifiedOn;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "CHALLAN_NO", nullable = false)
    private ChallanDetailHeader challanDetailHeader;
}
