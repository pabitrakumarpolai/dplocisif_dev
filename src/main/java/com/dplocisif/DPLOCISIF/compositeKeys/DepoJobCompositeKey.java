package com.dplocisif.DPLOCISIF.compositeKeys;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class DepoJobCompositeKey implements Serializable {
    private String depoCode;
    private Long jobCode;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof MenuUserAccessCompositeKey that)) return false;
//        return Objects.equals(depoCode, that.depoCode) && Objects.equals(jobCode, that.jobCode);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(depoCode, jobCode);
    }
}
