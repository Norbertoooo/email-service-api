# language: pt
# necessário revisar os passos

Funcionalidade: Enviar email

  Cenario: Enviar email com sucesso
    Dado que seja solicitado o envio de email com o seguintes dados:
      | emailDestino                   | conteudo                  | titulo              |
      | vithor.norberto@gmail.com      | um email para testar      | Email de teste um   |
    Então deverá efetuar o envio de email com sucesso
    E deverá constar os seguintes dados na base de dados:
      | emailDestino                   | conteudo                  | titulo              |  status  |
      | vithor.norberto@gmail.com      | um email para testar      | Email de teste um   |  SUCESSO |
    E o código de retorno da requisição deva ser 201
    E retornar a seguinte mensagem "Email enviado com sucesso!"

  Cenario: Erro ao enviar email
    Dado que seja solicitado o envio de email com o seguintes dados:
      | emailDestino                   | conteudo                  | titulo              |
      | vithor.norberto@gmail.com      | um email para testar      | Email de teste um   |
    Então deverá efetuar o envio de email com sucesso
    E deverá constar os seguintes dados na base de dados:
      | emailDestino                   | conteudo                  | titulo              |  status  |
      | vithor.norberto@gmail.com      | um email para testar      | Email de teste um   |  ERRO    |
    E o código de retorno da requisição deva ser 201
    E retornar a seguinte mensagem de erro "Erro ao tentar enviar email!"

  Cenario: Erro ao enviar email com campos inválidos
    Dado que seja solicitado o envio de email com o seguintes dados:
      | emailDestino                   | conteudo                  | titulo              |
      | vithor.norberto@gmail.com      |                           | Email de teste um   |
    Então deverá retornar erro 400
    E retornar a seguinte mensagem de erro "Campo conteudo não pode ser nulo!"

