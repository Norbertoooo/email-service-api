package com.vitu.email.service.api.web.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static com.vitu.email.service.api.utils.Constantes.*;

@Data
@Builder
public class EmailRequest {

    @Email(message = MENSAGEM_VALIDACAO_EMAIL_NAO_VALIDO)
    @NotBlank(message = MENSAGEM_VALICADACAO_EMAIL )
    private String emailDestino;

    @NotBlank(message = MENSAGEM_VALICADACAO_CONTEUDO)
    private String conteudo;

    @NotBlank(message = MENSAGEM_VALICADACAO_TITULO)
    private String titulo;

}
