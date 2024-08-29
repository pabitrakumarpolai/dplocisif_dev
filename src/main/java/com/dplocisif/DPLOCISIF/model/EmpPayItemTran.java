package com.dplocisif.DPLOCISIF.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_dcpy_emp_payitem_tran")
public class EmpPayItemTran {
    @Id
    @Column(name = "COMPANY_MARKER")
    private Long companyMarker;

    @Column(name = "NGS", length = 10, nullable = false)
    private String ngs;

    @Column(name = "SAL_MONTH", nullable = false)
    private Long salMonth;

    @Column(name = "SAL_YEAR", nullable = false)
    private Long salYear;

    @Column(name = "TAX_YEAR", nullable = false)
    private Long taxYear;

    @Column(name = "PAYITEM_ID", nullable = false)
    private Long payItemId;

    @Column(name = "PAYITEM_VALUE")
    private Long payItemValue;

    @Column(name = "PAYITEM_CUMULATIVE")
    private Long payItemCumulative;

    @Column(name = "CALC_VALUE")
    private Long calcValue;
}
