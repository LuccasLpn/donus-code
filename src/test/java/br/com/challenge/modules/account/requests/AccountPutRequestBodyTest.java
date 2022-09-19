package br.com.challenge.modules.account.requests;

import br.com.challenge.modules.account.entity.Account;
import br.com.challenge.modules.util.AccountCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountPutRequestBodyTest {

    AccountPutRequestBody expected = AccountCreator.createAccountPutRequestBody();

    @Test
    void getAgency() {
        Assertions.assertThat(expected.getAgency()).isNotNull();
    }

    @Test
    void getAccount() {
        Assertions.assertThat(expected.getAccount()).isNotNull();
    }

    @Test
    void getValue() {
        Assertions.assertThat(expected.getValue()).isNotNull();
    }

    @Test
    void setAgency() {
        var agency = "123";
        expected.setAgency(agency);
        Assertions.assertThat(expected.getAgency()).isNotNull();
        Assertions.assertThat(expected.getAgency()).isNotNull().isEqualTo(agency);
    }

    @Test
    void setAccount() {
        var account = "123456";
        expected.setAccount(account);
        Assertions.assertThat(expected.getAccount()).isNotNull();
        Assertions.assertThat(expected.getAccount()).isNotNull().isEqualTo(account);
    }

    @Test
    void setValue() {
        var value = 123456L;
        expected.setValue(value);
        Assertions.assertThat(expected.getValue()).isNotNull();
        Assertions.assertThat(expected.getValue()).isNotNull().isEqualTo(value);
    }

    @Test
    void builder() {
        AccountPutRequestBody build = AccountPutRequestBody.builder()
                .agency("1234")
                .account("123456")
                .value(1000L)
                .build();
        Assertions.assertThat(build).isNotNull();
    }

    @Test
    void noArgsConstructor(){
        AccountPutRequestBody account = new AccountPutRequestBody();
        Assertions.assertThat(account.getAccount()).isNull();
        Assertions.assertThat(account.getAgency()).isNull();
        Assertions.assertThat(account.getValue()).isNull();
    }
}