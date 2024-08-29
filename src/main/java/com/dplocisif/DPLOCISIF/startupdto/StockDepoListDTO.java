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
        name = "stockDepoListDTOMapping",
        classes = @ConstructorResult(
                targetClass = StockDepoListDTO.class,
                columns = {
                        @ColumnResult(name = "depo_code", type = String.class),
                        @ColumnResult(name = "depo_description", type = String.class),
                        @ColumnResult(name = "module_flag", type = String.class)
                }
        )
)
public class StockDepoListDTO {
    @Id
    private String depoCode;
    private String  depoDescription;
    private String moduleFlag;
}
