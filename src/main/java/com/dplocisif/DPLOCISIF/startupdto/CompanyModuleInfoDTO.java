package com.dplocisif.DPLOCISIF.startupdto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyModuleInfoDTO {
    private Long companyMarker;
    private String companyCode;
    private String companyName;
    private String companyAddress;
    private Long lastPayMonth;
    private Long lastPayYear;
    private Long finyearFrom;
    private Long finyearTo;
    private Long taxYear;
}
