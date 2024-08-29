package com.dplocisif.DPLOCISIF.startupdto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccessData {
    private String loginId;
    private String companyId;
    private String loginName;
    private String password;
    private String roleId;
    private String roleCode;
    private String companyName;
    private String roleName;
    private String menuId;
    private String moduleId;
    private String menuName;
    private String empId;
    private String moduleName;
    private String accessYN;
}
