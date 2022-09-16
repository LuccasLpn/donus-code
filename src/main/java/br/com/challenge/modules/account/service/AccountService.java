package br.com.challenge.modules.account.service;

import br.com.challenge.modules.account.entity.Account;
import br.com.challenge.modules.account.mapper.AccountMapper;
import br.com.challenge.modules.account.repository.AccountRepository;
import br.com.challenge.modules.account.requests.AccountCreatePostRequestBody;
import br.com.challenge.modules.account.requests.AccountPutRequestBody;
import br.com.challenge.modules.account.response.BalanceResponse;
import br.com.challenge.modules.exception.BadRequestException;
import br.com.challenge.modules.exception.PSQLException;
import br.com.challenge.modules.person.entity.Person;
import br.com.challenge.modules.util.Generation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    public AccountCreatePostRequestBody createAccount(AccountCreatePostRequestBody accountCreatePostRequestBody) {
        Generation generation = new Generation();
        Person person = AccountMapper.INSTANCE.toPerson(accountCreatePostRequestBody);
        person.setAuthorities("USER");
        Account account = Account
                .builder()
                .agency(generation.agencyGeneration())
                .account(generation.accountGeneration())
                .balance(0L)
                .person(person)
                .build();
        accountRepository.save(account);
        return accountCreatePostRequestBody;
    }
    public AccountPutRequestBody deposit(AccountPutRequestBody account) {
        accountRepository.deposit(account.getValue(),
                account.getAgency(),
                account.getAccount()).orElseThrow(() -> new PSQLException("Deposit failed"));
        return account;}

    public AccountPutRequestBody withdraw(AccountPutRequestBody account) {
        accountRepository.withdraw(account.getValue(),
                account.getAgency(),
                account.getAccount()).orElseThrow(() -> new PSQLException("Withdraw failed"));
        return account;}

    @Transactional
    public void transfer(AccountPutRequestBody account, String fullname, String cpf) {
        Account accountOut = accountRepository.findByFullNameAndCpf(fullname, cpf, account.getValue()).orElseThrow(() -> new BadRequestException("Agency or Count Not Found"));
        accountOut.setBalance(accountOut.getBalance() - account.getValue());
        Account accountIn = accountRepository.findByAgencyAndAccount(account.getAgency(), account.getAccount()).orElseThrow(() -> new BadRequestException("Agency or Count Not Found"));
        accountIn.setBalance(accountIn.getBalance() + account.getValue());
        accountRepository.save(accountIn);
        accountRepository.save(accountOut);
    }
    public Account findByIdOrThrowBadRequestException(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new BadRequestException("Account Not Found"));
    }
    public void delete(Long id){
        accountRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public BalanceResponse findBalance(String fullname){
        return BalanceResponse.builder()
                .balance(accountRepository.findByBalance(fullname).orElseThrow(() -> new RuntimeException("Account Not Found")).getBalance())
                .date(LocalDateTime.now())
                .build();
    }

}
