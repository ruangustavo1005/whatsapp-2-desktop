# WhatsApp 2 (Desktop)

## Requisitos

### Requisitos Funcionais

* RF01: O sistema deve permitir que o usuário faça login;
* RF02: O sistema deve permitir que o usuário faça seu cadastro na tela de login;
* RF03: O sistema deve listar todas as conversas do usuário logado;
* RF04: O sistema deve notificar o usuário quando ele receber uma mensagem;
* RF05: O sistema deve permitir que o usuário logado veja o histórico de mensagens de determinada conversa;
* RF06: O sistema deve permitir que o usuário envie mensagens em uma conversa;
* RF07: O sistema deve permitir que o usuário faça logout;
* RF08: O sistema deve atualizar as mensagens de uma conversa aberta, caso outro usuário da mesma conversa envie uma mensagem.

### Regras de Negócio

* RN01: Os dados de login são usuário (identificador único do usuário) e senha;
* RN02: Os dados de cadastro de um novo usuário são: nome completo, usuário e senha;
* RN03: As conversas de um usuário podem ser conversas privadas ou conversas em grupo;
* RN04: As conversas privadas contém apenas dois usuários;
* RN05: As conversar em grupo podem conter vários usuários;
* RN06: Os usuários de um grupo de conversa são podem ser alterados;
* RN07: O nome de usuário é único para cada usuário cadastrado no sistema.

### Requisitos Não Funcionais

* O sistema deve usar Sockets para fazer a comunicação entre cliente e servidor;
* O sistema deve usar Threads para fazer os processamentos após receber uma mensagem do cliente/servidor;
* O sistema deve fechar as conexões dos Sockets após enviar uma mensagem;
* O tipo do conteúdo das mensagens transitadas entre clienter e servidor deve ser em formato JSON.

## Mensagens trocadas

### Do cliente para o servidor

|Mensagem|Conteúdo|
|-|-|
|Consulta de usuário (se existe ou não, para login)|Nome de usuário do usuário que está tentando logar|
|Login|Usuário e senha (já em codificação MD5) do usuário que está tentando fazer login|
|Cadastro de novo usuário|Nome completo, usuário e senha do novo usuário|
|Consulta de conversas|Nome de usuário logado|
|Consulta de histórico de uma conversa|Nome de usuário logado e a conversa em questão|
|Envio de uma mensagem em determinada conversa|Nome de usuário do usuário logado, o identificador da conversa em questão e o conteúdo da mensagem|

### Do servidor para o cliente

|Mensagem|Conteúdo|
|-|-|
|Confirmação ou negação da existência de um usuário|Um booleano que indica se o usuário foi encontrado ou não|
|Confirmação ou negação de uma tentativa de login|Um booleano que indica se o usuário acertou suas credenciais de login|
|Listagem de conversas de um usuário|Uma lista de conversas, contendo o identificador, título da conversa e a quantidade de mensagens não lidas|
|Listagem de mensagens de uma conversa de determinado usuário|Uma lista de mensagens, contendo o usuário que a enviou, a data de envio e o conteúdo desta mensagem|
|Notificação de mensagem recebida por outro usuário|O identificador da conversa que recebeu a mensagem, o usuário que enviou ela, a data e o conteúdo dessa mensagem|