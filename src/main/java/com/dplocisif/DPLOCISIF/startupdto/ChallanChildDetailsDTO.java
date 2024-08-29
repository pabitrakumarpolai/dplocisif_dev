package com.dplocisif.DPLOCISIF.startupdto;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SqlResultSetMapping(
        name = "ChallanChildDetailsDTOMapping",
        classes = @ConstructorResult(
                targetClass = ChallanChildDetailsDTO.class,
                columns = {
                        @ColumnResult(name = "challan_srl_no", type = Long.class),
                        @ColumnResult(name = "item_code", type = String.class),
                        @ColumnResult(name = "folio_no", type = String.class),
                        @ColumnResult(name = "item_description", type = String.class),
                        @ColumnResult(name = "ord_itmsrl_no", type = String.class),
                        @ColumnResult(name = "challan_qty_in_number", type = Long.class), // adjust type as needed
                        @ColumnResult(name = "unit_code", type = String.class),
                        @ColumnResult(name = "unit_description", type = String.class)
                }
        )
)
public class ChallanChildDetailsDTO {
    @Id
    private Long challanSrlNo;
    private String itemCode;
    private String folioNo;
    private String itemDescription;
    private String ordItmsrlNo;
    private Long challanQtyInNumber;
    private String unitCode;
    private String unitDescription;
}
