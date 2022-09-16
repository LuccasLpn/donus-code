package br.com.challenge.modules.account.entity;

import br.com.challenge.modules.person.entity.Person;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "bank_account")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_account_seq")
    @SequenceGenerator(name = "bank_account_seq", sequenceName = "bank_account_seq", allocationSize = 1)
    private Long id;

    @Column(name = "agency")
    private String agency;

    @Column(name = "account")
    private String account;

    @Column(name = "balance")
    private Long balance;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id", unique = true)
    private Person person;
}
