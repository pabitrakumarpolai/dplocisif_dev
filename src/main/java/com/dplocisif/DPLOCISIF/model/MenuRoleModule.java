package com.dplocisif.DPLOCISIF.model;

import com.dplocisif.DPLOCISIF.compositeKeys.MenuRoleCompositeKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(MenuRoleCompositeKey.class)
@Entity
@Table(name = "t_dcst_menu_role_access")
public class MenuRoleModule {
    @Id
    @Column(name = "ROLE_ID", nullable = false)
    private Long roleId;
    @Id
    @Column(name = "MENU_ID", nullable = false)
    private Long menuId;
    @Column(name = "MODULE_ID", nullable = false)
    private Long moduleId;
}