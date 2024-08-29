package com.dplocisif.DPLOCISIF.model;

import com.dplocisif.DPLOCISIF.compositeKeys.DcpyMenuCompositeKey;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(DcpyMenuCompositeKey.class)
@Entity
@Table(name = "t_dcst_menu")
@BatchSize(size = 20)
public class MenuModule {
    @Id
    @Column(name = "MENU_ID")
    private Long menuId;
    @Id
    @Column(name = "MODULE_ID")
    private Long moduleId;
    @Column(name = "MENU_NAME")
    private String menuName;
    @Column(name = "MENU_LINK_NAME")
    private String menuLinkName;
    @Column(name = "MENU_REPORT_HEADING")
    private String menuReportHeading;
    @Column(name = "REPORT_PDF")
    private String reportPDF;
    @Column(name = "REPORT_XLS")
    private String reportXLS;
    @Column(name = "REPORT_TXT")
    private String reportTXT;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "MODULE_ID")
    Modules moduleMaster;
}
