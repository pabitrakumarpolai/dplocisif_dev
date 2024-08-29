package com.dplocisif.DPLOCISIF.Enum;

public enum CoreConstansts {
    STARTS(" -> "),
    ENDS(" <- "),
    DISTINCT_STCD(1),
    ALL_STCD(2),
    DEFAULT_COMPANY_MARKER (1), // for company code = DPL
    ZERO_INT (0),
    ZERO_STR ("0"),
    LOAN_TYPE_GF ("GF"),
    ITEM_INSPECTION_LENGTH(6),
    ITEM_INSPECTION_COL_NAME("inspection_no"),
    ITEM_INSPECTION_TABLE_NAME("t_dcst_t_item_inspection"),
    ITEM_INSPECTION_PREFIX("INS"),
    STOCK_ADJUSTMENT_COL_NAME("stock_adj_srl_no"),
    STOCK_ADJUSTMENT_TABLE_NAME("t_dcst_t_stock_adj");
    private Object value;
    private String data;
    CoreConstansts(String data) {
        this.data = data;
    }
    CoreConstansts(Object value){
        this.value = value;
    }
    public int getValue() {
        return (int) value;
    }
    public String getData() {
        return data;
    }
}
