package com.dplocisif.DPLOCISIF.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_DCST_M_APPROVER_LIST")
public class IndentApprover {

    @Id
    @GeneratedValue(generator = "Incremental")
    @GenericGenerator(
            name = "Incremental",
            strategy = "org.hibernate.id.IncrementGenerator"
    )
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPROVER_SRL_NO", nullable = false)
    private Long approverSrlNo;

    @Column(name = "DOCUMENT_TYPE", length = 50)
    private String documentType;

    @Column(name = "DEPARTMENT")
    private Long department;

    @Column(name = "LOGIN_ID")
    private Long loginId;

    @Column(name = "NGS", length = 30)
    private String ngs;

    @Column(name = "FROM_DATE")
    private Date fromDate;

    @Column(name = "TO_DATE")
    private Date toDate;

    @Column(name = "CREATED_BY")
    private Long createdBy;

    @Column(name = "CREATED_ON", nullable = false)
    private Date createdOn;

    @Column(name = "MODIFIED_BY")
    private Long modifiedBy;

    @Column(name = "MODIFIED_ON")
    private Date modifiedOn;
}
