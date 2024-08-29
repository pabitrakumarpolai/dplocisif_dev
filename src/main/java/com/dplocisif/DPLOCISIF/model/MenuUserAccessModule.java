package com.dplocisif.DPLOCISIF.model;

import com.dplocisif.DPLOCISIF.compositeKeys.MenuUserAccessCompositeKey;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@IdClass(MenuUserAccessCompositeKey.class)
@Table(name = "t_dcst_menu_user_access")
public class MenuUserAccessModule {
    @Id
    @Column(name = "LOGIN_ID")
    private Long loginId;

    @Id
    @Column(name = "MENU_ID")
    private Long menuId;

    @Column(name = "INCLUDE_EXCLUDE")
    private String includeExclude;

    @Column(name = "MODULE_ID")
    private Long moduleId;
}
