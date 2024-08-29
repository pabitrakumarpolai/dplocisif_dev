package com.dplocisif.DPLOCISIF.common;

import com.dplocisif.DPLOCISIF.Enum.DPLPAYConstants;
import com.dplocisif.DPLOCISIF.dplApplicationStartup.ApplicationStartup;
import com.dplocisif.DPLOCISIF.startupdto.CompanyModuleInfoDTO;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class CommonUtil {
    @Autowired
    ApplicationStartup applicationStartup;
    public int getSalMonth(int companyMarker) {
        long salMonth = 0;
        CompanyModuleInfoDTO dplpayCompanyData = applicationStartup.getCompanyModule(companyMarker);
        salMonth = dplpayCompanyData.getLastPayMonth();
        if (salMonth != (long) DPLPAYConstants.DECEMBER_MONTH.getValue()) {
            salMonth = salMonth + 1;
        } else {
            salMonth = (long) DPLPAYConstants.JANUARY_MONTH.getValue();
        }
        return (int) salMonth;
    }

    public int getSalYear(int companyMarker) {
        long salMonth = 0;
        long salYear = 0;
        CompanyModuleInfoDTO dplpayCompanyData = applicationStartup.getCompanyModule(companyMarker);
        salMonth = dplpayCompanyData.getLastPayMonth();
        salYear = dplpayCompanyData.getLastPayYear();
        if (salMonth == (long)DPLPAYConstants.DECEMBER_MONTH.getValue()) {
            salYear = salYear + 1;
        }
        return (int) salYear;
    }

    public String avoidNull(String inString) {
        if (StringUtils.isBlank(inString)) {
            return "";
        } else {
            return inString;
        }
    }

    public Date getCurrentTime() {
        long currentTimesInMillis = System.currentTimeMillis();
        return new Date(currentTimesInMillis);
    }

    public static Date stringToDate(String paramDateStr, String dateFormat) {
        Date formattedDate = null;

        try {
            SimpleDateFormat dtFormat = new SimpleDateFormat(dateFormat);
            formattedDate = dtFormat.parse(paramDateStr);
        } catch (Exception e) {

        }
        return formattedDate;
    }

}
