package com.dplocisif.DPLOCISIF.startupdto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuRoleModuleDTO {
    private String roleId;
    private String menuId;
    private String parentModuleName;
    private String parentModuleId;
    private String moduleId;
    private String moduleName;
    private String menuName;
    private String menuLinkName;
    private String roleDescription;
}
