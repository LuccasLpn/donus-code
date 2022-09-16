package br.com.challenge.modules.account.service;

import br.com.challenge.modules.account.entity.Account;
import br.com.challenge.modules.account.mapper.AccountMapper;
import br.com.challenge.modules.account.repository.AccountRepository;
import br.com.challenge.modules.account.requests.AccountCreatePostRequestBody;
import br.com.challenge.modules.account.requests.AccountPutRequestBody;
import br.com.challenge.modules.exception.BadRequestException;
import br.com.challenge.modules.exception.PSQLException;
import br.com.challenge.modules.person.entity.Person;
import br.com.challenge.modules.security.config.JwtUtil;
import br.com.challenge.modules.util.GenerationAccount;
import br.com.challenge.modules.util.GenerationAgency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    private final JwtUtil jwtUtil;

    @Transactional
    public AccountCreatePostRequestBody createAccount(AccountCreatePostRequestBody accountCreatePostRequestBody) {
        String generationAgency = new GenerationAgency().agencyGeneration();
        String generationAccount = new GenerationAccount().accountGeneration();
        Person person = AccountMapper.INSTANCE.toPerson(accountCreatePostRequestBody);
        person.setAuthorities("USER");
        Account account = Account
                .builder()
                .agency(generationAgency)
                .account(generationAccount)
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
        Account accountOut = accountRepository.findByFullNameAndCpf(fullname, cpf, account.getValue()).orElseThrow(() -> new BadRequestException("Agency or Account or Value not found"));
        accountOut.setBalance(accountOut.getBalance() - account.getValue());
        Account accountInto = accountRepository.findByAgencyAndAccount(account.getAgency(), account.getAccount())
                .orElseThrow(() -> new BadRequestException("Agency or Account not found"));
        accountInto.setBalance(accountInto.getBalance() + account.getValue());
        accountRepository.save(accountOut);
        accountRepository.save(accountInto);
    }
    public Account findByIdOrThrowBadRequestException(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new BadRequestException("Account Not Found"));
    }
    public void delete(Long id){
        accountRepository.delete(findByIdOrThrowBadRequestException(id));
    }

}
