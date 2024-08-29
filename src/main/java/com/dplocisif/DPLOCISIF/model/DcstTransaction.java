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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_dcst_m_transaction_code")
public class DcstTransaction {
    @Id
    @Column(name = "TRANSACTION_CODE",nullable = false)
    private Long transactionCode;

    @Column(name = "TRANS_DESCRIPTION", length = 100, nullable = false)
    private String transDescription;

    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy;

    @Column(name = "CREATED_ON", nullable = false)
    private Date createdOn;

    @Column(name = "MODIFIED_BY")
    private Long modifiedBy;

    @Column(name = "MODIFIED_ON")
    private Date modifiedOn;
}
