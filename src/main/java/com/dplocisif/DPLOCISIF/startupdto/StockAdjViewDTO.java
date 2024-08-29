package com.dplocisif.DPLOCISIF.startupdto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "vw_dcst_stock_adj")
public class StockAdjViewDTO {
    @Id
    @Column(name = "STOCK_ADJ_SRL_NO")
    private Long stockAdjSrlNo;

    @Column(name = "DEPO_CODE")
    private String depoCode;

    @Column(name = "DEPO_DESCRIPTION")
    private String depoDescription;

    @Column(name = "DEBIT_CREDIT")
    private String debitCredit;

    @Column(name = "ITEM_CODE")
    private String itemCode;

    @Column(name = "FOLIO_NO")
    private String folioNo;

    @Column(name = "ITEM_DESCRIPTION")
    private String itemDescription;

    @Column(name = "UNIT_CODE")
    private Long unitCode;

    @Column(name = "UNIT_DESCRIPTION")
    private String unitDescription;

//    @Column(name = "ADJUSTED_BY")
//    private Long adjustedBy;

//    @Column(name = "NGS")
//    private String ngs;

    @Column(name = "EMP_CODE")
    private String empCode;

    @Column(name = "EMP_NAME")
    private String empName;

    @Column(name = "ADJUSTED_ON")
    private Date adjustedOn;

    @Column(name = "ADJUSTED_QTY")
    private Long adjustedQty;

    @Column(name = "LOCATION_BIN_DESC")
    private String locationBinDesc;

    @Column(name = "BIN_NUMBER")
    private Long binNumber;

    @Column(name = "REMARKS")
    private String remarks;

}
