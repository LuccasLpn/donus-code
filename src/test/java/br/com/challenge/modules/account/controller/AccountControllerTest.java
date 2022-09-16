package br.com.challenge.modules.account.controller;

import br.com.challenge.modules.account.requests.AccountCreatePostRequestBody;
import br.com.challenge.modules.account.requests.AccountPutRequestBody;
import br.com.challenge.modules.account.service.AccountService;
import br.com.challenge.modules.security.config.JwtUtil;
import br.com.challenge.modules.util.AccountCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.HttpServletRequest;

@ExtendWith(SpringExtension.class)
@DisplayName("AccountControllerTest")
class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @BeforeEach
    void setUp() {

        BDDMockito.when(accountService.createAccount(ArgumentMatchers.any(AccountCreatePostRequestBody.class)))
                .thenReturn(AccountCreator.createAccountCreatePostRequestBody());

        BDDMockito.when(accountService.withdraw(ArgumentMatchers.any(AccountPutRequestBody.class)))
                .thenReturn(AccountCreator.createAccountPutRequestBody());

        BDDMockito.when(accountService.deposit(ArgumentMatchers.any(AccountPutRequestBody.class)))
                .thenReturn(AccountCreator.createAccountPutRequestBody());


        BDDMockito.doNothing().when(accountService).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("Saved Persist Account When Successful")
    void saved_Persist_Account_When_SucessFull() {
        AccountCreatePostRequestBody expected = AccountCreator.createAccountCreatePostRequestBody();
        AccountCreatePostRequestBody account = accountController.createAccount(AccountCreator.createAccountCreatePostRequestBody()).getBody();
        Assertions.assertThat(account).isNotNull();
        Assertions.assertThat(account.getFullName()).isNotNull().isEqualTo(expected.getFullName());
        Assertions.assertThat(account.getCpf()).isNotNull().isEqualTo(expected.getCpf());
    }

    @Test
    @DisplayName("Withdraw Persist Account When Successful")
    void withdraw_Persist_Person_When_SucessFull() {
        ResponseEntity<AccountPutRequestBody> entity = accountController.withdraw(AccountCreator.createAccountPutRequestBody());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Deposit Persist Account When Successful")
    void deposit_Persist_Person_When_SucessFull() {
        ResponseEntity<AccountPutRequestBody> entity = accountController.deposit(AccountCreator.createAccountPutRequestBody());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    @DisplayName("Delete Account When Sucessful")
    void delete_Account_When_SucessFull() {
        Assertions.assertThatCode(() -> accountController.delete(1L)).doesNotThrowAnyException();
        ResponseEntity<Void> entity = accountController.delete(1L);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}