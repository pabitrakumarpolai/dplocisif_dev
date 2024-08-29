package com.dplocisif.DPLOCISIF.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "t_dcst_user_login")
@NamedStoredProcedureQuery(
        name = "createLogin",
        procedureName = "DCST_CREATE_LOGIN",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "p_company_id"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_login_name"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_ngs"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_password"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "p_role_id"),
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "o_login_id")
        }
)
public class UserLoginModule {
    @Id
    @Column(name = "LOGIN_ID")
    private Long loginId;

    @Column(name = "COMPANY_MARKER")
    private Long companyMarker;

    @Column(name = "NGS", nullable = false)
    private String ngs;

    @Column(name = "LOGIN_NAME", nullable = false)
    private String loginName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ROLE_ID", nullable = false)
    private Long roleId;

    @Column(name = "CREATED_BY", columnDefinition = "VARCHAR2(30) default USER")
    private String createdBy;

    @Column(name = "CREATED_DATE", columnDefinition = "DATE default SYSDATE")
    private Date createdDate;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;

    @Column(name = "DEACTIVATION_DATE")
    private Date deactivationDate;
}
