package br.com.challenge.modules.account.repository;

import br.com.challenge.modules.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = """
            update bank_account set balance = balance + :value
            where :value <= 2000
            and agency = :agency
            and account = :account
            RETURNING agency, account, balance, id, person_id
            """, nativeQuery = true)
    Optional<Account> deposit(Long value, String agency, String account);

    @Query(value = """
            update bank_account ba set balance = balance - :value
            where balance >= :value
            and ba.agency = :agency
            and ba.account = :account
            RETURNING agency, account, balance, id, person_id
            """, nativeQuery = true)
    Optional<Account> withdraw(Long value, String agency, String account);


    @Query(value = """
            select ba.id, ba.agency, ba.account, ba.balance, ba.person_id from bank_account as ba
            left join person p on p.id = ba.person_id
            where p.full_name = :fullname
            and p.cpf = :cpf
            and ba.balance >= :value
            """, nativeQuery = true)
    Optional<Account> findByFullNameAndCpf(String fullname, String cpf, Long value);

    @Query(value = """
        select ba.id, ba.agency, ba.account, ba.balance, ba.person_id from bank_account as ba
        left join person p on p.id = ba.person_id
        where p.full_name = :fullname
        """, nativeQuery = true)
    Optional<Account> findByBalance(String fullname);

    Optional<Account> findByAgencyAndAccount(String agency, String account);
}
