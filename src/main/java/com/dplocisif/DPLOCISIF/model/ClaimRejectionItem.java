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

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "T_DCST_T_CLAIM_REJ_ITEM")
public class ClaimRejectionItem {

    @Id
    @Column(name = "CLAIM_NO")
    private String claimNo;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "CLAIM_DATE")
    private Date claimDate;

    @Column(name = "INSPECTION_NO", nullable = false)
    private String inspectionNo;

    @Column(name = "QTY_CLAIMED")
    private Long qtyClaimed;

    @Column(name = "DISCREPANCY_NOTED", length = 50)
    private String discrepancyNoted;

    @Column(name = "SENT_TO_PARTY", length = 1)
    private String sentToParty;

    @Column(name = "CLOSE_CLAIM", length = 1)
    private String closeClaim;

    @Column(name = "REMARKS", length = 500)
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
