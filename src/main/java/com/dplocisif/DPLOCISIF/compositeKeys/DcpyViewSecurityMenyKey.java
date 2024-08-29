package com.dplocisif.DPLOCISIF.compositeKeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
@BatchSize(size = 20)
public class DcpyViewSecurityMenyKey implements Serializable {
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "module_id")
    private Long moduleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DcpyViewSecurityMenyKey that)) return false;
        return Objects.equals(roleId, that.roleId) && Objects.equals(menuId, that.menuId) && Objects.equals(moduleId, that.moduleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, menuId, moduleId);
    }
}
