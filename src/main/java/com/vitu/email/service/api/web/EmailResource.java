package com.vitu.email.service.api.web;

import com.vitu.email.service.api.service.EmailService;
import com.vitu.email.service.api.web.request.EmailRequest;
import com.vitu.email.service.api.web.response.EmailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/enviar-email")
@RequiredArgsConstructor
@Log4j2
public class EmailResource {

    private final EmailService emailService;

    @PostMapping()
    public ResponseEntity<EmailResponse> enviarEmail(@Valid @RequestBody EmailRequest emailRequest) {
        log.info("Requisição para envio de email com o seguinte corpo: {}", emailRequest);
        return ResponseEntity.status(CREATED).body(emailService.enviarEmail(emailRequest));
    }

    // TODO: 10/08/2021 implementar camada de serviço
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarEmailPorId(@PathVariable String id) {
        log.info("Requisição para buscar email pelo id: {}", id);
        return ResponseEntity.ok().build();
    }
}
