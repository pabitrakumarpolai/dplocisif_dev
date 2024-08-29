package com.dplocisif.DPLOCISIF.service;

import com.dplocisif.DPLOCISIF.dplApplicationStartup.ApplicationStartup;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Service class for managing reloading of the servlet.
 */
@Service
public class ReloadService {
    @Autowired
    ApplicationStartup applicationStartup;

    /**
     * Reloads the servlet based on the given admin action.
     * @param result The result map containing the outcome of the reload operation.
     * @param object The JSON object containing the admin action.
     * @throws JSONException If there is an error in JSON processing.
     */

    public void reloadServlet(Map<String, String> result, String object) throws JSONException {
        JSONObject jsonObject = new JSONObject(object);
        String adminAction = jsonObject.has("data") ? (String) jsonObject.get("data") : "";
        if (adminAction.equals("REFRESH_CACHE")) {
            applicationStartup.startUp();
            result.put("success", "Cache Refresh Successfully");
        }
    }
}
