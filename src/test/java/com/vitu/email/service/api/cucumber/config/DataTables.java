package com.vitu.email.service.api.cucumber.config;

import com.vitu.email.service.api.domain.Email;
import com.vitu.email.service.api.domain.Status;
import com.vitu.email.service.api.web.request.EmailRequest;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class DataTables {

    @DataTableType
    public EmailRequest emailRequestDataTable(Map<String, String> map) {
        return EmailRequest.builder()
                .emailDestino(map.get("emailDestino"))
                .conteudo(map.get("conteudo"))
                .titulo(map.get("titulo"))
                .build();
    }

    @DataTableType
    public Email emailDataTable(Map<String, String> map) {
        return Email.builder()
                .emailDestino(map.get("emailDestino"))
                .conteudo(map.get("conteudo"))
                .titulo(map.get("titulo"))
                .status(Status.valueOf(map.get("status")))
                .build();
    }
}
