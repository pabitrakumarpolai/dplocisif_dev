package com.dplocisif.DPLOCISIF.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_dcst_menu_module")
public class Modules {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MODULE_ID")
    private Long id;
    @Column(name = "MODULE_NAME")
    private String moduleName;
    @OneToMany(mappedBy = "moduleMaster", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<MenuModule> menuModuleList;
}
