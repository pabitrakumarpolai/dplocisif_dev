package com.dplocisif.DPLOCISIF.startupdto;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SqlResultSetMapping(
        name = "getTransactionCode",
        classes = @ConstructorResult(
                targetClass = TransactionCodeDTO.class,
                columns = {
                        @ColumnResult(name = "transaction_code", type = Long.class),
                        @ColumnResult(name = "trans_description", type = String.class)
                }
        )
)
public class TransactionCodeDTO {
    @Id
    private Long transactionCode;
    private String transDescription;
}
