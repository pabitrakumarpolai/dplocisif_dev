package com.dplocisif.DPLOCISIF.model;

import com.dplocisif.DPLOCISIF.compositeKeys.DepoJobCompositeKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(DepoJobCompositeKey.class)
@Table(name = "T_DCST_M_DEPOWISE_JOB")
public class DepoJobModule {
    @Id
    @Column(name = "DEPO_CODE", nullable = false)
    private String depoCode;

    @Column(name = "JOB_CODE", nullable = false)
    private Long jobCode;

    @Column(name = "JOB_DESCRIPTION", nullable = false, length = 500)
    private String jobDescription;

    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy;

    @Column(name = "CREATED_ON", nullable = false)
    private Date createdOn;

    @Column(name = "MODIFIED_BY")
    private Long modifiedBy;

    @Column(name = "MODIFIED_ON")
    private Date modifiedOn;

}
