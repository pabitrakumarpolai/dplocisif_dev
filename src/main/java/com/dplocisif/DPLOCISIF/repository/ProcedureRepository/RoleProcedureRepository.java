package com.dplocisif.DPLOCISIF.repository.ProcedureRepository;

import com.dplocisif.DPLOCISIF.startupdto.MenuRoleAccessDTO;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@Repository
public class RoleProcedureRepository {
    @Autowired
    EntityManager entityManager;
    @Autowired
    DataSource dataSource;

    public void addRoleDescription(String roleDescription, Long loginId) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("dcst_dpl_master_pg.set_role_pr");
        storedProcedureQuery.registerStoredProcedureParameter("p_role_desc", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_login_id", Long.class, ParameterMode.IN);

        storedProcedureQuery.setParameter("p_role_desc", roleDescription);
        storedProcedureQuery.setParameter("p_login_id", loginId);

        storedProcedureQuery.execute();
    }

    @Cacheable(value = "getMenuByRoleId", key = "#roleId")
    public Map<String, HashMap<String, List<MenuRoleAccessDTO>>> getMenuRoleAccess(String roleId) {
        Map<String, HashMap<String, List<MenuRoleAccessDTO>>> map = new TreeMap<>();
        if (!StringUtils.isBlank(roleId)) {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("dcst_dpl_master_pg.get_menu_role_access_pr", "MenuRoleAccessDTO");
            storedProcedureQuery.registerStoredProcedureParameter("p_role_id", Long.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("p_RefCur_role", void.class, ParameterMode.REF_CURSOR);

            storedProcedureQuery.setParameter("p_role_id", roleId);
            List<MenuRoleAccessDTO> menuRoleAccessList = storedProcedureQuery.getResultList();

            menuRoleAccessList.forEach(role -> {
                if (map.containsKey(role.getParentModuleName())) {
                    HashMap<String, List<MenuRoleAccessDTO>> menuAgainstRoleMap = map.get(role.getParentModuleName());
                    if (menuAgainstRoleMap.containsKey(role.getModuleName())) {
                        List<MenuRoleAccessDTO> menuRoleAccessDTOList = menuAgainstRoleMap.get(role.getModuleName());
                        menuRoleAccessDTOList.add(role);
                        menuAgainstRoleMap.put(role.getModuleName(), menuRoleAccessDTOList);
                    } else {
                        List<MenuRoleAccessDTO> menuRoleAccessDTOList = new ArrayList<>();
                        menuRoleAccessDTOList.add(role);
                        menuAgainstRoleMap.put(role.getModuleName(), menuRoleAccessDTOList);
                    }
                    map.put(role.getParentModuleName(), menuAgainstRoleMap);
                } else {
                    HashMap<String, List<MenuRoleAccessDTO>> menuAgainstRoleMap = new HashMap<>();
                    List<MenuRoleAccessDTO> menuRoleAccessDTOList = new ArrayList<>();
                    menuRoleAccessDTOList.add(role);
                    menuAgainstRoleMap.put(role.getModuleName(), menuRoleAccessDTOList);
                    map.put(role.getParentModuleName(), menuAgainstRoleMap);
                }
            });
        }
        return map;
    }

    public void addRoleMenuAccess(String roleId, String menuList) throws SQLException {
        Connection connection = dataSource.getConnection();
        Clob clob = connection.createClob();
        clob.setString(1, menuList);

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("dcst_dpl_master_pg.set_menu_role_access_pr");
        storedProcedureQuery.registerStoredProcedureParameter("p_role_id", Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("p_menu_id", Clob.class, ParameterMode.IN);


        storedProcedureQuery.setParameter("p_role_id", roleId);
        storedProcedureQuery.setParameter("p_menu_id", clob);

        storedProcedureQuery.execute();
    }
}
