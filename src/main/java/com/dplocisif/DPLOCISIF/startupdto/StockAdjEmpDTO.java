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
        name = "stockAdjEmpDTOMapping",
        classes = @ConstructorResult(
                targetClass = StockAdjEmpDTO.class,
                columns = {
                        @ColumnResult(name = "login_id", type = long.class),
                        @ColumnResult(name = "ngs", type = String.class),
                }
        )
)
public class StockAdjEmpDTO {
    @Id
    private Long loginId;
    private String  ngs;
}
