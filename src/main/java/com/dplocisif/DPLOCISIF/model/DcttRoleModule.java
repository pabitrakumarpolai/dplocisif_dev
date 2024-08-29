package com.dplocisif.DPLOCISIF.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_dctt_roles")
public class DcttRoleModule {
    @Id
    @Column(name = "ROLE_ID")
    private Long roleId;
    @Column(name = "ROLE_CODE")
    private String roleCode;
    @Column(name = "ROLE_DESCRIPTION")
    private Long roleDescription;
}
