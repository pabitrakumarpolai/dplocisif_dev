package com.dplocisif.DPLOCISIF.startupdto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SqlResultSetMapping(
        name = "ClaimRejectedItemKeyDTO",
        classes = @ConstructorResult(
                targetClass = ClaimRejectedItemKeyGenDTO.class,
                columns = {
                        @ColumnResult(name = "inspection_no", type = String.class),
                        @ColumnResult(name = "inspection_date", type = Date.class),
                        @ColumnResult(name = "challan_no", type = String.class),
                        @ColumnResult(name = "qty_accepted", type = Long.class),
                        @ColumnResult(name = "qty_rejected", type = Long.class),
                        @ColumnResult(name = "discrepancy_noted", type = String.class), // adjust type as needed
                        @ColumnResult(name = "item_code", type = String.class),
                        @ColumnResult(name = "folio_no", type = String.class),
                        @ColumnResult(name = "item_description", type = String.class),
                        @ColumnResult(name = "unit_code", type = Long.class),
                        @ColumnResult(name = "unit_description", type = String.class)
                }
        )
)
public class ClaimRejectedItemKeyGenDTO {
    @Id
    private String inspectionNo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "IST")
    private Date inspectionDate;
    private String challanNo;
    private Long qtyAccepted;
    private Long qtyRejected;
    private String discrepancyNoted;
    private String itemCode;
    private String folioNo;
    private String itemDescription;
    private Long unitCode;
    private String unitDescription;
}
