package com.dplocisif.DPLOCISIF.transformer;


import com.dplocisif.DPLOCISIF.model.DepartModule;
import com.dplocisif.DPLOCISIF.startupdto.DepartmentMouldeInfoDTO;

import java.util.ArrayList;
import java.util.List;

public class DepartmentModuleTransformer {
    public static List<DepartmentMouldeInfoDTO> depertModulesTODepertmentModuleInfo(List<DepartModule> departModules) {
        List<DepartmentMouldeInfoDTO> departmentMouldeInfoDTOS = new ArrayList<>();
        departModules.forEach(department -> {
            departmentMouldeInfoDTOS.add(convertDepartment(department));
        });
        return departmentMouldeInfoDTOS;
    }

    private static DepartmentMouldeInfoDTO convertDepartment(DepartModule department) {
        return DepartmentMouldeInfoDTO.builder()
                .deptCode(department.getId())
                .deptName(department.getDeptName())
                .build();
    }
}
