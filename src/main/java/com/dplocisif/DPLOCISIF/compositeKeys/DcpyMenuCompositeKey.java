package com.dplocisif.DPLOCISIF.compositeKeys;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class DcpyMenuCompositeKey implements Serializable {
    private Long menuId;
    private Long moduleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DcpyMenuCompositeKey that)) return false;
        return Objects.equals(menuId, that.menuId) && Objects.equals(moduleId, that.moduleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, moduleId);
    }
}
