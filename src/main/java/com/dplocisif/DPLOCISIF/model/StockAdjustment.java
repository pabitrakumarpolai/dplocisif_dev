package com.dplocisif.DPLOCISIF.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_DCST_T_STOCK_ADJ")
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockAdjustment {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "Incremental")
//    @GenericGenerator(
//            name = "Incremental",
//            strategy = "org.hibernate.id.IncrementGenerator"
//    )
    @Column(name = "STOCK_ADJ_SRL_NO", nullable = false)
    private Long stockAdjSrlNo;

    @Column(name = "DEPO_CODE", length = 5)
    private String depoCode;

    @Column(name = "ITEM_CODE", length = 8)
    private String itemCode;

    @Column(name = "UNIT_CODE")
    private Long unitCode;

    @Column(name = "DEBIT_CREDIT_FLAG", length = 1)
    private String debitCreditFlag;

    @Column(name = "ADJUSTED_QTY")
    private Long adjustedQty;

    @Column(name = "ADJUSTED_BY")
    private Long adjustedBy;

    @Column(name = "ADJUSTED_ON")
    private Date adjustedOn;

    @Column(name = "LOCATION_BIN_DESC", length = 200)
    private String locationBinDesc;

    @Column(name = "BIN_NUMBER")
    private Long binNumber;

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
