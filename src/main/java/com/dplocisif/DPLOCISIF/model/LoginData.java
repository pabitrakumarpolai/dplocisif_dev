package com.dplocisif.DPLOCISIF.model;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginData {
    public BigDecimal location_id;
    public BigDecimal login_id;
    public BigDecimal role_id;
    public String ngs;
    public BigDecimal companyMarker;
    public String loginName;
    public String password;
    public String companyName;
    public BigDecimal lastPayMonth;
    public BigDecimal lastPayYear;
    public BigDecimal finyearFrom;
    public BigDecimal finyearTo;
    public String empName;
}
