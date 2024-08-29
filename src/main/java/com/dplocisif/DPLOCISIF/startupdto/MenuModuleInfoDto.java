package com.dplocisif.DPLOCISIF.startupdto;

import com.dplocisif.DPLOCISIF.compositeKeys.DcpyViewSecurityMenyKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.Immutable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "vw_DCST_security_menu")
@BatchSize(size = 20)
public class MenuModuleInfoDto {
    @EmbeddedId
    DcpyViewSecurityMenyKey dcpyViewSecurityMenyKey;
    private String menuName;
    private String menuLinkName;
    private Long loginId;
    private String loginName;
    private String moduleName;
    private String companyMarker;
    private String companyName;
    private String ngs;
    private String menuReportHeading;
    private String reportXls;
    private String reportPdf;
    private String reportTxt;
    @Column(name = "FINYEAR_FROM")
    private int finYearFrom;
    @Column(name = "FINYEAR_TO")
    private int finYearTo;
    private Long parentModuleId;
    private String parentModuleName;
}