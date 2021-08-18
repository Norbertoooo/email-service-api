package com.vitu.email.service.api.cucumber.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitu.email.service.api.domain.Email;
import com.vitu.email.service.api.repository.EmailRepository;
import com.vitu.email.service.api.service.EmailService;
import com.vitu.email.service.api.web.request.EmailRequest;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j2
public class EnviarEmailSteps {

    @Autowired
    private MockMvc mockMvc;

    private EmailRepository emailRepository;

    private ResultActions resultActions;

    @Dado("que seja solicitado o envio de email com o seguintes dados:")
    public void queSejaSolicitadoOEnvioDeEmailComOSeguintesDados(List<EmailRequest> emailRequest) throws Exception {
        log.info(emailRequest.stream().findFirst());

        String json = new ObjectMapper().writeValueAsString(emailRequest.stream().findFirst().orElseThrow());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/enviar-email")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        this.resultActions = mockMvc.perform(requestBuilder);

    }

    @Então("deverá efetuar o envio de email com sucesso")
    public void deveráEfetuarOEnvioDeEmailComSucesso() throws Exception {
        resultActions.andExpect(status().isCreated());
    }

    @Então("deverá retornar erro 400")
    public void deveráRetornarErro400() throws Exception {
        resultActions.andExpect(status().isBadRequest());
    }

    @Então("deverá retornar erro 502")
    public void deveráRetornarErro502() throws Exception {
        resultActions.andExpect(status().isBadGateway());
    }

    @E("retornar a seguinte mensagem {string}")
    public void retornaASeguinteMensagem(String mensagem) throws Exception {
        log.info(mensagem);
        resultActions.andExpect(jsonPath("mensagem").value(mensagem)).andDo(print());
    }

    @E("retornar a seguinte mensagem de erro {string}")
    public void retornaASeguinteMensagemDeErro(String mensagem) throws Exception {
        log.info(mensagem);
        String retorno = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info(retorno);
        assert retorno.contains(mensagem);
    }

    @E("deverá constar os seguintes dados na base de dados:")
    public void deveráConstarOsSeguintesDadosNaBaseDeDados(List<Email> emails) throws Exception {
        log.info("Emails: {}", emails);
        Email email = emails.stream().findFirst().orElseThrow();
        log.info(email);
        resultActions.andExpect(jsonPath("email.status").value(email.getStatus().toString())).andDo(print());
    }

    @E("o código de retorno da requisição deva ser {int}")
    public void oCódigoDeRetornoDaRequisiçãoDevaSer(int codigoRetorno) throws Exception {
        log.info("Código retorno: {}", codigoRetorno);
        resultActions.andExpect(status().is(codigoRetorno));
    }
}
