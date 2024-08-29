package com.dplocisif.DPLOCISIF.service;

import com.dplocisif.DPLOCISIF.repository.ProcedureRepository.TransactionCodeRepository;
import com.dplocisif.DPLOCISIF.startupdto.TransactionCodeDTO;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TransactionCodeService {
    @Autowired
    TransactionCodeRepository transactionCodeRepository;

    public void getTransactionCodes(Map<String, Object> result, String object) throws JSONException {
        JSONObject jsonObject = new JSONObject(object);
        Long transactionCode = jsonObject.has("transCode") ? jsonObject.getLong("transCode") : null;
        List<TransactionCodeDTO> transactionCodeDTOS = transactionCodeRepository.getTransactionCodeList(transactionCode);
        result.put("success", transactionCodeDTOS);
    }
}
