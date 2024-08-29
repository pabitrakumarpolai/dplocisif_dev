package com.dplocisif.DPLOCISIF.startupdto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SqlResultSetMapping(
        name = "GetPoItemListPr",
        classes = @ConstructorResult(
                targetClass = PoItemListPrDTO.class,
                columns = {
                        @ColumnResult(name = "ord_itmsrl_no", type = Long.class),
                        @ColumnResult(name = "ord_itm_desc", type = String.class),
                        @ColumnResult(name = "ord_itm_qty", type = String.class),
                        @ColumnResult(name = "ord_itm_qty_uom", type = String.class),
                        @ColumnResult(name = "depo_code", type = String.class), // adjust type as needed
                        @ColumnResult(name = "ord_item_code", type = String.class),
                        @ColumnResult(name = "ord_item_rate", type = String.class),
                        @ColumnResult(name = "item_desc", type = String.class),
                        @ColumnResult(name = "folio_no", type = String.class),
                }
        )
)
public class PoItemListPrDTO {
    @Id
    private Long ordItmsrlNo;
    private String ordItmDesc;
    private String ordItmQty;
    private String ordItmQtyUom;
    private String depoCode;  // Adjust type as needed
    private String ordItemCode;
    private String ordItemRate;
    private String itemDesc;
    private String folioNo;
}
