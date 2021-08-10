package com.vitu.email.service.api.service;

import com.vitu.email.service.api.web.exception.BasicException;
import com.vitu.email.service.api.web.request.EmailRequest;
import com.vitu.email.service.api.web.response.EmailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.vitu.email.service.api.utils.Constantes.ERRO_AO_ENVIAR_EMAIL;
import static com.vitu.email.service.api.utils.Constantes.SUCESSO_AO_ENVIAR_EMAIL;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailResponse enviarEmail(EmailRequest emailRequest) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(emailRequest.getTitulo());
        simpleMailMessage.setText(emailRequest.getConteudo());
        simpleMailMessage.setTo(emailRequest.getEmailDestino());

        javaMailSender.send(simpleMailMessage);

        return EmailResponse.builder().mensagem(SUCESSO_AO_ENVIAR_EMAIL).build();
    }
}
