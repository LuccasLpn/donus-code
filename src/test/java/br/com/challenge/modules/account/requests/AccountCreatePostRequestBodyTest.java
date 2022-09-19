package br.com.challenge.modules.account.requests;

import br.com.challenge.modules.util.AccountCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AccountCreatePostRequestBodyTest {
    AccountCreatePostRequestBody expected = AccountCreator.createAccountCreatePostRequestBody();

    @Test
    void getFullName() {

        Assertions.assertThat(expected.getFullName()).isNotNull();
    }

    @Test
    void getCpf() {
        Assertions.assertThat(expected.getCpf()).isNotNull();
    }

    @Test
    void setFullName() {
        var name = "Luiz Miguel Bastos";
        expected.setFullName(name);
        Assertions.assertThat(expected.getFullName()).isNotNull();
        Assertions.assertThat(expected.getFullName()).isNotNull().isEqualTo(name);
    }

    @Test
    void setCpf() {
        var cpf = "48188562822";
        expected.setCpf(cpf);
        Assertions.assertThat(expected.getCpf()).isNotNull();
        Assertions.assertThat(expected.getCpf()).isNotNull().isEqualTo(cpf);
    }

    @Test
    void builder() {
        AccountCreatePostRequestBody builder = AccountCreatePostRequestBody.builder()
                .fullName("Luccas Pereira Nunes")
                .cpf("48188562823")
                .build();
        Assertions.assertThat(builder.getCpf()).isNotNull();
        Assertions.assertThat(builder.getFullName()).isNotNull();
    }


    @Test
    void noArgsConstructor(){
        AccountCreatePostRequestBody accountCreatePostRequestBody = new AccountCreatePostRequestBody();
        Assertions.assertThat(accountCreatePostRequestBody.getCpf()).isNull();
        Assertions.assertThat(accountCreatePostRequestBody.getFullName()).isNull();
    }

}