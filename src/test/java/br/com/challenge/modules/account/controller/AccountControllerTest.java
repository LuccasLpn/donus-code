package br.com.challenge.modules.account.controller;

import br.com.challenge.modules.account.entity.Account;
import br.com.challenge.modules.account.requests.AccountCreatePostRequestBody;
import br.com.challenge.modules.account.requests.AccountPutRequestBody;
import br.com.challenge.modules.account.service.AccountService;
import br.com.challenge.modules.person.entity.Person;
import br.com.challenge.modules.security.config.JwtUtil;
import br.com.challenge.modules.util.AccountCreator;
import br.com.challenge.modules.util.PersonCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ExtendWith(SpringExtension.class)
@DisplayName("AccountControllerTest")
class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;


    @Mock
    private AccountService accountService;

    private static HttpServletRequest request = new MockHttpServletRequest();

    @BeforeEach
    void setUp() {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.addHeader("Authorization", "Bearer " +
                "eyJhbGciOiJIUzI1NiJ9.eyJjcGYiOiI0ODE4ODU2MjgyMyIsImlkIjoiMSIsImZ1bGxuYW1lIjoiTHVjY2FzIFBlcmVpcmEgTnVuZXMiLCJleHAiOjE2NjMzMDA1Njd9.D-iu38Fk3u6v0WCd0ErGSqd4fZ9-PWsMIERwI-2Gc24");
        request = mockRequest;

        BDDMockito.when(accountService.createAccount(ArgumentMatchers.any(AccountCreatePostRequestBody.class)))
                .thenReturn(AccountCreator.createAccountCreatePostRequestBody());

        BDDMockito.when(accountService.withdraw(ArgumentMatchers.any(AccountPutRequestBody.class)))
                .thenReturn(AccountCreator.createAccountPutRequestBody());

        BDDMockito.when(accountService.deposit(ArgumentMatchers.any(AccountPutRequestBody.class)))
                .thenReturn(AccountCreator.createAccountPutRequestBody());

        BDDMockito.doNothing().when(accountService).transfer(ArgumentMatchers.any(AccountPutRequestBody.class),
                ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
    }

    @Test
    @DisplayName("Saved Persist Account When SucessFull")
    void saved_Persist_Account_When_SucessFull() {
        AccountCreatePostRequestBody expected = AccountCreator.createAccountCreatePostRequestBody();
        AccountCreatePostRequestBody account = accountController.createAccount(AccountCreator.createAccountCreatePostRequestBody()).getBody();
        Assertions.assertThat(account).isNotNull();
        Assertions.assertThat(account.getFullName()).isNotNull().isEqualTo(expected.getFullName());
        Assertions.assertThat(account.getCpf()).isNotNull().isEqualTo(expected.getCpf());
    }

    @Test
    @DisplayName("Withdraw Persist Account When SucessFull")
    void withdraw_Persist_Person_When_SucessFull() {
        ResponseEntity<AccountPutRequestBody> entity = accountController.withdraw(AccountCreator.createAccountPutRequestBody());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Deposit Persist Account When SucessFull")
    void deposit_Persist_Person_When_SucessFull() {
        ResponseEntity<AccountPutRequestBody> entity = accountController.deposit(AccountCreator.createAccountPutRequestBody());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Transfer Persist Account When SucessFull")
    void transfer_Persist_Person_When_SucessFull() {
        Assertions.assertThatCode(() -> accountController.transfer(AccountCreator.createAccountPutRequestBody(), request)).doesNotThrowAnyException();
        ResponseEntity<Void> entity = accountController.transfer(AccountCreator.createAccountPutRequestBody(), request);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}