package com.dplocisif.DPLOCISIF.startupdto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ItemInspectionFilterDTO {
    private String depoCode;
    private String depoDescription;
    private String inspectionNo;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date inspectionDate;
    private String chalanNo;
    private String itemCode;
    private String itemDescription;
    private Long challanQtyInNumber;
}
