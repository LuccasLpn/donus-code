package br.com.challenge.modules.account.requests;

import br.com.challenge.modules.util.AccountCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTransferPutRequestBodyTest {

    AccountTransferPutRequestBody expected = AccountCreator.createAccountTransferPutRequestBody();

    @Test
    void getAccountDestiny() {
        Assertions.assertThat(expected.getAccountDestiny()).isNotNull();
    }

    @Test
    void getAgency() {
        Assertions.assertThat(expected.getAgency()).isNotNull();
    }

    @Test
    void getAccount() {
        Assertions.assertThat(expected.getAccount()).isNotNull();
    }

    @Test
    void setAccountDestiny() {
        AccountPutRequestBody accountDestiny = expected.getAccountDestiny();
        accountDestiny.setAgency("1233");
        accountDestiny.setAccount("1233456");
        accountDestiny.setValue(1233456L);
        Assertions.assertThat(accountDestiny.getAccount()).isNotNull();
        Assertions.assertThat(accountDestiny.getAgency()).isNotNull();
        Assertions.assertThat(accountDestiny.getValue()).isNotNull();
    }

    @Test
    void setAgency() {
        var agency ="123";
        expected.setAgency(agency);
        Assertions.assertThat(expected.getAgency()).isNotNull().isEqualTo(agency);
    }

    @Test
    void setAccount() {
        var account ="1234567";
        expected.setAccount(account);
        Assertions.assertThat(expected.getAccount()).isNotNull().isEqualTo(account);
    }

    @Test
    void builder() {
        AccountTransferPutRequestBody builder = AccountTransferPutRequestBody
                .builder()
                .accountDestiny(AccountCreator.createAccountPutRequestBody())
                .agency("2865")
                .account("306954")
                .build();
        Assertions.assertThat(builder).isNotNull();
    }

    @Test
    void noArgsConstructor(){
        AccountTransferPutRequestBody account = new AccountTransferPutRequestBody();
        Assertions.assertThat(account.getAccount()).isNull();
        Assertions.assertThat(account.getAgency()).isNull();
        Assertions.assertThat(account.getAccountDestiny()).isNull();
    }
}