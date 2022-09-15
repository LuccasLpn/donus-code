package br.com.challenge.modules.person.repository;

import br.com.challenge.modules.person.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByFullName(String fullname);

    Optional<Person> findByFullNameAndCpf(String fullname, String cpf);
}
