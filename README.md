# donus-code

## Construído com as seguintes tecnologias
```
* Spring Boot 
* Java
* Jwt
* Mapstruct
* Flyway
* OpenAPi
* Hibernate
* JPA
* Lombok
* Actuator
* Prometheus
* Grafana
```

## Executando o aplicativo localmente
```
- Faça o clone o repositório via https "git clone https://github.com/LuccasLpn/donus-code.git"
- Abra a Pasta src/main/resources
- Execute -> Linux "sudo docker-compose up -d"
- Execute -> Windows "docker-compose up -d"
- Abra o IntelliJ IDEA
- Open -> Navegue até a pasta onde está o projeto
- Selecione o projeto clonado do git
- Escolha o arquivo de inicialização do Spring (pesquise @SpringBootApplication)
- Clique com o botão direito do mouse no arquivo e execute
```
## Criando Uma Conta
```
- Use o endpoint "http://localhost:7079/challenge/account/create"
- Coloque esse JsonObject ->
{
  "fullName": "string",
  "cpf": "string"
}
- Esse endpoint cria uma conta e pessoa ao mesmo tempo, fazendo o vinculo.
- Por padrão toda pessoa vai ser cadastrada como user
```
## Login
```
- Use o endpoint "http://localhost:7079/challenge/auth"
- Coloque esse JsonObject ->
{
  "fullName": "string",
  "cpf": "string"
}
- Retornando HttpStatus 200 Ok, pegue o token que será gerado nos Headers da request
- Esse Token deve ser passado nas próximo requisições
```