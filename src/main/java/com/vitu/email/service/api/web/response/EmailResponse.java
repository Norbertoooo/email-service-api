package com.vitu.email.service.api.web.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailResponse {

    private String mensagem;

}
