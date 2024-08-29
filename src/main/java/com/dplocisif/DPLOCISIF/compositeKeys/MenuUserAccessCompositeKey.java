package com.dplocisif.DPLOCISIF.compositeKeys;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class MenuUserAccessCompositeKey implements Serializable {
    private Long loginId;
    private Long menuId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuUserAccessCompositeKey that)) return false;
        return Objects.equals(loginId, that.loginId) && Objects.equals(menuId, that.menuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginId, menuId);
    }
}
