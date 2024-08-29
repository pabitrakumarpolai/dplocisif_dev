package com.dplocisif.DPLOCISIF.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "T_DCST_T_ITEM_INSPECTION")
public class ItemInspection {
    @Id
    @Column(name = "INSPECTION_NO")
    private String inspectionNo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "IST")
    @Column(name = "INSPECTION_DATE")
    private Date inspectionDate;

    @Column(name = "CHALLAN_NO")
    private String challanNo;

    @Column(name = "CHALLAN_SRL_NO")
    private Long challanSerialNumber;

    @Column(name = "ITEM_CODE")
    private String itemCode;

    @Column(name = "QTY_ACCEPTED")
    private Double qtyAccepted;

    @Column(name = "QTY_REJECTED")
    private Double qtyRejected;

    @Column(name = "DISCREPANCY_NOTED")
    private String discrepancyNoted;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy;

    @Column(name = "CREATED_ON", nullable = false)
    private Date createdOn;

    @Column(name = "MODIFIED_BY")
    private Long modifiedBy;

    @Column(name = "MODIFIED_ON")
    private Date modifiedOn;
}
