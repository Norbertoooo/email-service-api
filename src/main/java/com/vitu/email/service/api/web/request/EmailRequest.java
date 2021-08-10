package com.vitu.email.service.api.web.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class EmailRequest {

    @Email(message = "Email informado não válido!")
    @NotBlank(message = "Campo email não pode ser nulo!")
    private String emailDestino;

    @NotBlank(message = "Campo conteudo não pode ser nulo!")
    private String conteudo;

    @NotBlank(message = "Campo titulo não pode ser nulo!")
    private String titulo;

}
