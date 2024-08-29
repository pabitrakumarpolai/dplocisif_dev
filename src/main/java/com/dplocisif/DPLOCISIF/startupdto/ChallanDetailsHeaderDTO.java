package com.dplocisif.DPLOCISIF.startupdto;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@SqlResultSetMapping(
        name = "challanDetailsHeader",
        classes = @ConstructorResult(
                targetClass = ChallanDetailsHeaderDTO.class,
                columns = {
                        @ColumnResult(name = "challan_no", type = String.class),
                        @ColumnResult(name = "challan_date", type = Date.class),
                        @ColumnResult(name = "po_no", type = String.class),
                        @ColumnResult(name = "po_date", type = Date.class),
                        @ColumnResult(name = "vendor_code", type = String.class),
                        @ColumnResult(name = "vendor_name", type = String.class),
                        @ColumnResult(name = "depo_code", type = String.class),
                        @ColumnResult(name = "depo_description", type = String.class)
                }
        )
)
public class ChallanDetailsHeaderDTO {
    @Id
    private String challanNo;
    private Date challanDate;
    private String poNo;
    private Date poDate;
    private String vendorCode;
    private String vendorName;
    private String depoCode;
    private String depoDescription;
}
