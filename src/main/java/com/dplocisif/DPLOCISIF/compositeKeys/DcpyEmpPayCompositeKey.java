package com.dplocisif.DPLOCISIF.compositeKeys;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class DcpyEmpPayCompositeKey implements Serializable {
    private Long companyMarker;
    private String ngs;
    private Long salMonth;
    private Long salYear;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DcpyEmpPayCompositeKey that)) return false;
        return Objects.equals(companyMarker, that.companyMarker) && Objects.equals(ngs, that.ngs) && Objects.equals(salMonth, that.salMonth) && Objects.equals(salYear, that.salYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyMarker, ngs, salMonth, salYear);
    }
}
