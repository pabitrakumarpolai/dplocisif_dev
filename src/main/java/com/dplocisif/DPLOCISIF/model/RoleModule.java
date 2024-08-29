package com.dplocisif.DPLOCISIF.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_dcst_roles")
public class RoleModule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROLE_ID")
    private Long id;
    @Column(name = "ROLE_CODE")
    private String role;
    @Column(name = "ROLE_DESCRIPTION")
    private String roleDesc;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "DEACTIVATION_DATE")
    private Date deactivationDate;
    @Column(name = "MODIFIED_Date")
    private Date modifiedDate;
}
