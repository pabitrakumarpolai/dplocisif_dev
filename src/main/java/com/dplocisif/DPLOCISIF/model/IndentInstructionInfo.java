package com.dplocisif.DPLOCISIF.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "T_DCST_M_INDENT_INSTRUCTION")
public class IndentInstructionInfo
{
    @Id
    @GeneratedValue(generator = "Incremental")
    @GenericGenerator(
            name = "Incremental",
            strategy = "org.hibernate.id.IncrementGenerator"
    )
    @Column(name = "INSTRUCTION_SRL_NO")
    private Long instSrlNo;
    @Column(name = "SRL_NO",nullable = false)
    private Long srlNo;
    @Column(name = "INSTRUCTION_DESC ",nullable = false)
    private String instructDesc;
    @Column(name = "IS_ACTIVE")
    private String isActive;
    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy;
    @Column(name = "CREATED_ON ",nullable = false)
    private Date createdOn;
    @Column(name = "MODIFIED_BY")
    private Long modifiedBy;
    @Column(name = "MODIFIED_ON")
    private Date modifiedOn;
}
