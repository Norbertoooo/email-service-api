package com.vitu.email.service.api.web.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

import static com.vitu.email.service.api.utils.Constantes.DATA_HORA_BR;

@Data
@Builder
public class BasicException {

    private Map<String, String> erros;
    @JsonFormat(pattern=DATA_HORA_BR)
    private LocalDateTime dataHora;
    private Integer codigo;

}
