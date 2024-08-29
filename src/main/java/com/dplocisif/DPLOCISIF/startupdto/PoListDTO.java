package com.dplocisif.DPLOCISIF.startupdto;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SqlResultSetMapping(
        name = "getPoListPr",
        classes = @ConstructorResult(
                targetClass = PoListDTO.class,
                columns = {
                        @ColumnResult(name = "ord_ref_number", type = String.class),
                        @ColumnResult(name = "ord_date", type = Date.class),
                        @ColumnResult(name = "ord_vendor", type = String.class),
                        @ColumnResult(name = "vendor_name", type = String.class)
                }
        )
)
public class PoListDTO {
    @Id
    private String ordRefNumber;
    private Date ordDate;
    private String ordVendor;
    private String vendorName;
}
