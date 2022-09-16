package br.com.challenge.modules.account.repository;

import br.com.challenge.modules.account.entity.Account;
import br.com.challenge.modules.util.AccountCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DisplayName("AccountRepositoryTest")
@DataJpaTest
class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("Save Persist Account When Successful")
    void save_Persist_Account_When_SucessFull() {
        Account account = AccountCreator.createAccountToBeSaved();
        Account accountSaved = accountRepository.save(account);
        Assertions.assertThat(accountSaved).isNotNull();
        Assertions.assertThat(accountSaved.getId()).isNotNull().isEqualTo(account.getId());
        Assertions.assertThat(accountSaved.getPerson().getFullName()).isNotNull().isEqualTo(account.getPerson().getFullName());
    }

    @Test
    @DisplayName("Save Update Account When Successful")
    void save_Update_Account_When_SucessFull() {
        Account account = AccountCreator.createAccountToBeSaved();
        Account accountSaved = accountRepository.save(account);
        accountSaved.setBalance(2000L);
        Account accountUpdate = accountRepository.save(accountSaved);
        Assertions.assertThat(accountUpdate).isNotNull();
        Assertions.assertThat(accountUpdate.getId()).isNotNull().isEqualTo(accountSaved.getId());
    }

    @Test
    @DisplayName("Delete Remove Account When SuccessFull")
    void delete_Remove_Account_When_SuccessFull() {
        Account account = AccountCreator.createAccountToBeSaved();
        Account accountSaved = accountRepository.save(account);
        accountRepository.delete(accountSaved);
        Optional<Account> accountOptional = accountRepository.findById(accountSaved.getId());
        Assertions.assertThat(accountOptional.isEmpty());
    }

    @Test
    @DisplayName("FindAll Account When SuccessFull")
    void findAll_Account_When_SuccessFull() {
        Account account = AccountCreator.createAccountToBeSaved();
        accountRepository.save(account);
        List<Account> accounts = accountRepository.findAll();
        Assertions.assertThat(accounts)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(accounts.get(0).getId()).isEqualTo(account.getId());
    }

    @Test
    @DisplayName("findById Account When SuccessFull")
    void findById_Account_When_SuccessFull() {
        Account account = AccountCreator.createAccountToBeSaved();
        Account accountSaved = accountRepository.save(account);
        Optional<Account> accountOptional = accountRepository.findById(accountSaved.getId());
        Assertions.assertThat(accountOptional).isNotNull();
    }

}