package com.dplocisif.DPLOCISIF.service;

import com.dplocisif.DPLOCISIF.repository.UserLoginModuleRepository;
import com.dplocisif.DPLOCISIF.startupdto.UserLoginNameDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class UserLoginService {
    @Autowired
    UserLoginModuleRepository userLoginModuleRepository;
    private ObjectMapper objectMapper;

    public List<UserLoginNameDTO> getAllUserLogin(Map<String, Object> result, String object) throws JSONException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JSONObject jsonObject = new JSONObject(object);
        int pageSize = jsonObject.getInt("pageSize");
        int pageNumber = jsonObject.getInt("pageNumber");

        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        List<Object[]> userLoginModulesList = userLoginModuleRepository.findByLoginIdAndNam(pageable);

        List<UserLoginNameDTO> userLoginModules = userLoginModulesList.stream()
                .map(user -> new UserLoginNameDTO(((BigDecimal) user[0]).longValue(), (String) user[1], (String) user[2]))
                .collect(Collectors.toList());
        result.put("success",userLoginModules);
        return userLoginModules;
    }

}
