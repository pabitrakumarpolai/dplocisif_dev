package com.dplocisif.DPLOCISIF.service;

import com.dplocisif.DPLOCISIF.model.DepartModule;
import com.dplocisif.DPLOCISIF.repository.DepartmentModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * DplDepartmentService provides methods for retrieving department information.
 */
@Service
public class DplDepartmentService {
    @Autowired
    DepartmentModuleRepository dplDepartmentRepository;

    /**
     * Retrieves all departments.
     * @param result A Map to store the operation result.
     */
    public void getAllDepartment(Map<String, Object> result) {
        List<DepartModule> departments = dplDepartmentRepository.findAll();
        result.put("success", departments);
    }

}
