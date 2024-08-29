package com.dplocisif.DPLOCISIF.startupdto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@SqlResultSetMapping(
        name = "stockAdjustment",
        classes = @ConstructorResult(
                targetClass = StockAdjustmentDTO.class,
                columns = {
                        @ColumnResult(name = "item_code", type = String.class),
                        @ColumnResult(name = "folio_no", type = String.class),
                        @ColumnResult(name = "item_description", type = String.class),
                        @ColumnResult(name = "unit_code", type = long.class),
                        @ColumnResult(name = "unit_description", type = String.class),
                        @ColumnResult(name = "bin_number", type = long.class),
                        @ColumnResult(name = "stock_quantity", type = long.class),
                        @ColumnResult(name = "location_bin_desc", type = String.class)
                }
        )
)
public class StockAdjustmentDTO {
    @Id
    private String itemCode;
    private String  folioNo;
    private String itemDescription;
    private Long unitCode;
    private String unitDescription;
    private Long binNumber;
    private Long stockQuantity;
    private String locationBinDesc;

}
