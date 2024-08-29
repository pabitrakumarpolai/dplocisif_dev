package com.dplocisif.DPLOCISIF.startupdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserLoginNameDTO {
    private String ngs;
    private String nam;
    private Long loginId;

    public UserLoginNameDTO(Long loginId, String ngs, String nam) {
        this.ngs = ngs;
        this.nam = nam;
        this.loginId = loginId;
    }

}
