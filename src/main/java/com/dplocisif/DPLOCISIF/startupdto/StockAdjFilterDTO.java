package com.dplocisif.DPLOCISIF.startupdto;

import lombok.Data;

@Data
public class StockAdjFilterDTO {
    private String depoCode;
    private String depoDesription;
    private String itemCode;
    private String itemDescription;
    private String debitCreditFlag;
    private Long adjustedQty;

}
