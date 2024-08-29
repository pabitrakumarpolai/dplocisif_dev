package com.dplocisif.DPLOCISIF.model;

import com.dplocisif.DPLOCISIF.compositeKeys.DcpySTCDCompositeKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(DcpySTCDCompositeKey.class)
@Table(name = "t_dcpy_stcd")
public class StcdModule {
    @Id
    @Column(name = "STCD_TYPE")
    private String StcdType;
    @Id
    @Column(name = "STCD_KEY")
    private String StcdKey;
    @Column(name = "STCD_DESC")
    private String stcdDesc;
    @Column(name = "GROUP1_CODE")
    private String group1Code;
    @Column(name = "GROUP2_CODE")
    private String group2Code;
}
