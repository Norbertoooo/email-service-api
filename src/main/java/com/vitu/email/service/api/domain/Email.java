package com.vitu.email.service.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import static com.vitu.email.service.api.utils.Constantes.DATA_HORA_BR;

@Data
@Builder
@Document(collection = "Email")
public class Email {

    @Id
    private String id;
    private String emailDestino;
    private String conteudo;
    private String titulo;
    @JsonFormat(pattern=DATA_HORA_BR)
    private LocalDateTime dataHoraEnvio;
    private Status status;
}
