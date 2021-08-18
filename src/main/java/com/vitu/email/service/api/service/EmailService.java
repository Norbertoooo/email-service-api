package com.vitu.email.service.api.service;

import com.vitu.email.service.api.domain.Email;
import com.vitu.email.service.api.repository.EmailRepository;
import com.vitu.email.service.api.web.request.EmailRequest;
import com.vitu.email.service.api.web.response.EmailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.vitu.email.service.api.domain.Status.ERRO;
import static com.vitu.email.service.api.domain.Status.SUCESSO;
import static com.vitu.email.service.api.utils.Constantes.ERRO_AO_ENVIAR_EMAIL;
import static com.vitu.email.service.api.utils.Constantes.SUCESSO_AO_ENVIAR_EMAIL;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final EmailRepository emailRepository;

    public EmailResponse enviarEmail(EmailRequest emailRequest) throws MailException {

        Email email = Email.builder().build();

        EmailResponse emailResponse = EmailResponse.builder().build();

        log.info("email vazio: {}", email);

        BeanUtils.copyProperties(emailRequest, email);

        log.info("Email após uso do beanUtils: {}", email);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(emailRequest.getTitulo());
        simpleMailMessage.setText(emailRequest.getConteudo());
        simpleMailMessage.setTo(emailRequest.getEmailDestino());

        try {
            email.setDataHoraEnvio(LocalDateTime.now());
            email.setStatus(SUCESSO);
            emailResponse.setMensagem(SUCESSO_AO_ENVIAR_EMAIL);
            javaMailSender.send(simpleMailMessage);
        } catch (MailException mailException) {
            log.error("Erro ao enviar email: {}", mailException.getLocalizedMessage());
            email.setStatus(ERRO);
            emailResponse.setMensagem(ERRO_AO_ENVIAR_EMAIL);
        } finally {
            Email emailSalvo = emailRepository.save(email);
            emailResponse.setEmail(emailSalvo);
            log.info("Email salvo: {}", emailSalvo);
        }

        return emailResponse;

    }
}
