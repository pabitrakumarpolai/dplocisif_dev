package com.dplocisif.DPLOCISIF.compositeKeys;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class DcpySTCDCompositeKey implements Serializable {
    private String StcdType;
    private String StcdKey;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DcpySTCDCompositeKey that)) return false;
        return Objects.equals(StcdType, that.StcdType) && Objects.equals(StcdKey, that.StcdKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(StcdType, StcdKey);
    }
}
