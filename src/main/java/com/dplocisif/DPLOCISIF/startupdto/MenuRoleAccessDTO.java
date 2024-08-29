package com.dplocisif.DPLOCISIF.startupdto;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SqlResultSetMapping(
        name = "MenuRoleAccessDTO",
        classes = @ConstructorResult(
                targetClass = MenuRoleAccessDTO.class,
                columns = {
                        @ColumnResult(name = "module_id", type = Long.class),
                        @ColumnResult(name = "module_name", type = String.class),
                        @ColumnResult(name = "menu_id", type = Long.class),
                        @ColumnResult(name = "menu_name", type = String.class),
                        @ColumnResult(name = "menu_link_name", type = String.class),
                        @ColumnResult(name = "menu_report_heading", type = String.class),
                        @ColumnResult(name = "report_pdf", type = String.class),
                        @ColumnResult(name = "report_xls", type = String.class),
                        @ColumnResult(name = "report_txt", type = String.class),
                        @ColumnResult(name = "is_access", type = String.class),
                        @ColumnResult(name = "parent_module_id", type = Long.class),
                        @ColumnResult(name = "parent_module_name", type = String.class)
                }
        )
)
public class MenuRoleAccessDTO {
    @Id
    private Long moduleId;
    private String moduleName;
    private Long menuId;
    private String menuName;
    private String menuLinkName;
    private String menuReportHeading;
    private String reportPdf;
    private String reportXls;
    private String reportTxt;
    private String isAccess;
    private Long parentModuleId;
    private String parentModuleName;
}
