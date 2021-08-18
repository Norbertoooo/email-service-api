package com.vitu.email.service.api.web.response;

import com.vitu.email.service.api.domain.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailResponse {

    private String mensagem;
    private Email email;

}
