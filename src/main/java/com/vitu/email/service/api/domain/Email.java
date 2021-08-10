package com.vitu.email.service.api.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "Email")
public class Email {

    @Id
    private String id;
    private String emailDestino;
    private String conteudo;
    private String titulo;
    private LocalDateTime dataHoraEnvio;
    private Status status;
}
