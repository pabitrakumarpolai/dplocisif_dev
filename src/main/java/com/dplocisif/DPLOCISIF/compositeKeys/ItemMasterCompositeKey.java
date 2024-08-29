package com.dplocisif.DPLOCISIF.compositeKeys;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ItemMasterCompositeKey implements Serializable {
    private String depoCode;
    private String itemCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemMasterCompositeKey that)) return false;
        return Objects.equals(depoCode, that.depoCode) && Objects.equals(itemCode, that.itemCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depoCode, itemCode);
    }
}
