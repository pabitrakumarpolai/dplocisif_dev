package com.dplocisif.DPLOCISIF.model;

import com.dplocisif.DPLOCISIF.compositeKeys.ItemMasterCompositeKey;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ItemMasterCompositeKey.class)
@Entity
@Table(name = "T_DCST_M_ITEM")
public class ItemMaster {
    @Id
    @Column(name = "DEPO_CODE", nullable = false)
    private String depoCode;
    @Id
    @Column(name = "ITEM_CODE", nullable = false)
    private String itemCode;
    @Column(name = "ITEM_DESCRIPTION")
    private String itemDescription;
    @Column(name = "STOCK_QUANTITY")
    private Double stockQuantity;
    @Column(name = "MIN_STOCK_LEVEL")
    private Double minStockLevel;
    @Column(name = "MAX_STOCK_LEVEL")
    private Double maxStockLevel;
    @Column(name = "REORDER_LEVEL")
    private Double reorderLevel;
    @Column(name = "WT_AVG_RATE")
    private Double weightedAverageRate;
    @Column(name = "VED_FLAG")
    private String vedFlag;
    @Column(name = "LOCATION_BIN_DESC")
    private String locationBinDesc;
    @Column(name = "BIN_NUMBER")
    private Long binNumber;
    @Column(name = "GROUP_CODE")
    private String groupCode;
    @Column(name = "FOLIO_NO")
    private String folioNo;
    @Column(name = "UNIT_CODE")
    private Long unitCode;
    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy; //login id
    @Column(name = "CREATED_ON", nullable = false)
    private Date createdOn; //system date
    @Column(name = "MODIFIED_BY")
    private Long modifiedBy; //login id
    @Column(name = "MODIFIED_ON")
    private Date modifiedOn; //system date
}
