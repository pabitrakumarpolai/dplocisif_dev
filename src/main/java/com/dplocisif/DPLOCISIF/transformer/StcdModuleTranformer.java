package com.dplocisif.DPLOCISIF.transformer;


import com.dplocisif.DPLOCISIF.model.StcdModule;
import com.dplocisif.DPLOCISIF.startupdto.StcdModuleInfoDTO;

import java.util.ArrayList;
import java.util.List;

public class StcdModuleTranformer {
    public static List<StcdModuleInfoDTO> stcdModuleToStcdModuleInfoDto(List<StcdModule> stcdModules) {
        List<StcdModuleInfoDTO> stcdModuleInfoDTOS = new ArrayList<>();
        stcdModules.forEach(stcd -> {
            stcdModuleInfoDTOS.add(convertStcd(stcd));
        });
        return stcdModuleInfoDTOS;
    }

    private static StcdModuleInfoDTO convertStcd(StcdModule stcd) {
        return StcdModuleInfoDTO.builder()
                .salHead(stcd.getStcdType())
                .salDesc(stcd.getStcdDesc())
                .build();
    }
}
