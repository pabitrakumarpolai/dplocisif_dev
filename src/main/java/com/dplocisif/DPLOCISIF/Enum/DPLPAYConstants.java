package com.dplocisif.DPLOCISIF.Enum;

public enum DPLPAYConstants {
    PORTAL_PROPERTY_FILE,
    LOG4J_PROPERTY_FILE,
    DPLPAY_HOST,
    DPLPAY_PORT,
    DPLPAY_USERNAME,
    DPLPAY_PASSWORD,
    DPLPAY_DBName,
    DPLPAY_ORACLE_ENV_ID (1),
    MASSUPLOAD_DIR,
    DOWNLOAD_TYPE,
    UPLOAD_TYPE,
    DPLPAY_DB_TYPE,
    DECEMBER_MONTH (12),
    JANUARY_MONTH (1),
    LOAD_TIME_CARD,
    LOAD_SAT_OT,
    LOCATION  ("L"),
    GRADE ("GRADE"),
    DESIGNATION ("DESIG"),
    DEPARTMENT ("DEPT"),
    EMPLOYEE_TYPE ("EMPTYPE"),
    CCR ("CCR"),
    LEFTSTATUS ("LEFTSTATUS"),
    BANK ("BANK"),
    SMTP_HOST_NAME,
    SUPPORT_EMAIL,
    PDF_REPORT_FORMAT ("pdf"),
    TXT_REPORT_FORMAT ("txt"),
    XLS_REPORT_FORMAT ("xls"),
    FORMAT ("FORMAT"),
    STARTS (" -> "),
    ENDS (" <- "),
    YES("Y"),
    NO ("N"),
    ALL ("ALL"),
    TRACES_DIR;

    private Object value;

    DPLPAYConstants(Object value) {
        this.value = value;
    }

    DPLPAYConstants() {}

    public Object getValue() {
        return value;
    }
}
