package br.com.challenge.modules.account.service;

import br.com.challenge.modules.account.entity.Account;
import br.com.challenge.modules.account.repository.AccountRepository;
import br.com.challenge.modules.account.requests.AccountCreatePostRequestBody;
import br.com.challenge.modules.account.requests.AccountPutRequestBody;
import br.com.challenge.modules.person.entity.Person;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DisplayName("AccountServiceTest")
class AccountServiceTest {


    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp(){

        BDDMockito.when(accountRepository.save(ArgumentMatchers.any(Account.class)))
                .thenReturn(AccountCreator.createAccountToValid());

        BDDMockito.when(accountRepository.deposit(ArgumentMatchers.anyLong(),ArgumentMatchers.anyString(),ArgumentMatchers.anyString()))
                .thenReturn(Optional.ofNullable(AccountCreator.createAccountToValid()));

        BDDMockito.when(accountRepository.withdraw(ArgumentMatchers.anyLong(),ArgumentMatchers.anyString(),ArgumentMatchers.anyString()))
                .thenReturn(Optional.ofNullable(AccountCreator.createAccountToValid()));

        BDDMockito.when(accountRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(AccountCreator.createAccountToValid()));


        BDDMockito.when(accountRepository.findByFullNameAndCpf(ArgumentMatchers.anyString(),ArgumentMatchers.anyString(),ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(AccountCreator.createAccountToValid()));

        BDDMockito.when(accountRepository.findByAgencyAndAccount(ArgumentMatchers.anyString(),ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(AccountCreator.createAccountToValid()));

        BDDMockito.doNothing().when(accountRepository).delete(ArgumentMatchers.any(Account.class));

    }

    @Test
    @DisplayName("Saved Persist Account When Successful")
    void saved_Persist_Account_When_SucessFull() {
        Person person = PersonCreator.createPersonToBeSaved();
        AccountCreatePostRequestBody accountCreate = accountService.createAccount(AccountCreator.createAccountCreatePostRequestBody());
        Assertions.assertThat(accountCreate).isNotNull();
        Assertions.assertThat(accountCreate.getFullName()).isNotNull().isEqualTo(person.getFullName());
        Assertions.assertThat(accountCreate.getCpf()).isNotNull().isEqualTo(person.getCpf());
    }

    @Test
    @DisplayName("Withdraw Persist Account When Successful")
    void withdraw_Persist_Person_When_SucessFull() {
        var value =  3000 - 1500;
        Account expected = AccountCreator.createAccountToValid();
        AccountPutRequestBody withdraw = accountService.withdraw(AccountCreator.createAccountPutRequestBody());
        Assertions.assertThat(withdraw).isNotNull();
        Assertions.assertThat(withdraw.getAgency()).isNotNull().isEqualTo(expected.getAgency());
        Assertions.assertThat(withdraw.getValue()).isNotNull().isEqualTo(value);
    }

    @Test
    @DisplayName("Deposit Persist Account When Successful")
    void deposit_Persist_Person_When_SucessFull() {
        var value =  1500;
        Account expected = AccountCreator.createAccountToValid();
        AccountPutRequestBody accountDeposit = accountService.deposit(AccountCreator.createAccountPutRequestBody());
        Assertions.assertThat(accountDeposit).isNotNull();
        Assertions.assertThat(accountDeposit.getAgency()).isNotNull().isEqualTo(expected.getAgency());
        Assertions.assertThat(accountDeposit.getAccount()).isNotNull().isEqualTo(expected.getAccount());
        Assertions.assertThat(accountDeposit.getValue()).isNotNull().isEqualTo(value);
    }

    @Test
    @DisplayName("Transfer Persist Account When Successful")
    void transfer_Persist_Person_When_SucessFull() {
        Assertions.assertThatCode(() -> accountService.transfer(AccountCreator.createAccountPutRequestBody(), "Luccas Pereira Nunes", "48188562823"));
    }

    @Test
    @DisplayName("FindByIdOrThrowBadRequestException")
    void findByIdOrThrowBadRequestException() {
        Account expected = AccountCreator.createAccountToValid();
        Account account = accountService.findByIdOrThrowBadRequestException(1L);
        Assertions.assertThat(account).isNotNull();
        Assertions.assertThat(account.getId()).isNotNull().isEqualTo(expected.getId());
    }

    @Test
    @DisplayName("Delete Account When Successful")
    void delete_Account_When_SucessFull() {
        Assertions.assertThatCode(() -> accountService.delete(1L)).doesNotThrowAnyException();
        ;
    }


}