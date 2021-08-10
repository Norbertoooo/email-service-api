package com.vitu.email.service.api.web.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.vitu.email.service.api.utils.Constantes.ERRO_AO_ENVIAR_EMAIL;
import static org.springframework.http.HttpStatus.BAD_GATEWAY;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Log4j2
public class ExceptionAdvice {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BasicException handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return BasicException.builder()
                .dataHora(LocalDateTime.now())
                .codigo(BAD_REQUEST.value())
                .erros(errors)
                .build();
    }

    @ResponseStatus(BAD_GATEWAY)
    @ExceptionHandler(MailException.class)
    public BasicException handleMailException(MailException ex) {
        log.error("Erro ao tentar enviar email", ex);
        return BasicException.builder()
                .erros(Map.of("mensagem", ERRO_AO_ENVIAR_EMAIL))
                .dataHora(LocalDateTime.now())
                .codigo(BAD_GATEWAY.value())
                .build();
    }

}
