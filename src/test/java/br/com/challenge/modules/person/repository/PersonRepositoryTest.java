package br.com.challenge.modules.person.repository;

import br.com.challenge.modules.person.entity.Person;
import br.com.challenge.modules.person.requests.PersonPostRequestBody;
import br.com.challenge.modules.util.PersonCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DisplayName("PersonRepositoryTest")
@DataJpaTest
class PersonRepositoryTest{

    @Autowired
    private PersonRepository personRepository;

    @Test
    @DisplayName("Save Persist Person When Successful")
    void save_Persist_Person_When_SucessFull(){
        Person person = PersonCreator.createPersonToBeSaved();
        Person personSaved = personRepository.save(person);
        Assertions.assertThat(personSaved).isNotNull();
        Assertions.assertThat(personSaved.getId()).isNotNull();
    }

    @Test
    @DisplayName("Save Update Person When Successful")
    void save_Update_Person_When_SucessFull(){
        Person person = PersonCreator.createPersonToBeSaved();
        Person personSaved = personRepository.save(person);
        personSaved.setAuthorities("Admin");
        Person personUpdate = personRepository.save(personSaved);
        Assertions.assertThat(personUpdate).isNotNull();
        Assertions.assertThat(personUpdate.getId()).isNotNull();
        Assertions.assertThat(personUpdate.getAuthorities()).isNotNull().isEqualTo(personSaved.getAuthorities());
    }

    @Test
    @DisplayName("Delete Remove Person When SuccessFull")
    void delete_Remove_Person_When_SuccessFull(){
        Person person = PersonCreator.createPersonToBeSaved();
        Person personSaved = personRepository.save(person);
        personRepository.delete(personSaved);
        Optional<Person> personOptional = personRepository.findById(personSaved.getId());
        Assertions.assertThat(personOptional.isEmpty());
    }

    @Test
    @DisplayName("FindAll Person When SuccessFull")
    void findAll_Person_When_SuccessFull(){
        Person person = PersonCreator.createPersonToBeSaved();
        personRepository.save(person);
        List<Person> persons = personRepository.findAll();
        Assertions.assertThat(persons)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(persons.get(0).getFullName()).isEqualTo(person.getFullName());
    }

    @Test
    @DisplayName("findById Person When SuccessFull")
    void findById_Person_When_SuccessFull(){
        Person person = PersonCreator.createPersonToBeSaved();
        Person personSaved = personRepository.save(person);
        Optional<Person> findByIdPerson = personRepository.findById(personSaved.getId());
        Assertions.assertThat(findByIdPerson).isNotNull();
    }

    @Test
    @DisplayName("findByFullName Person When SuccessFull")
    void findByFullName_Person_When_SuccessFull(){
        Person person = PersonCreator.createPersonToBeSaved();
        Person personSaved = personRepository.save(person);
        Person findByFullName = personRepository.findByFullName(personSaved.getFullName());
        Assertions.assertThat(findByFullName).isNotNull();
    }

    @Test
    @DisplayName("findByFullNameAndCpf Person When SuccessFull")
    void findByFullNameAndCpf_Person_When_SuccessFull(){
        Person person = PersonCreator.createPersonToBeSaved();
        Person personSaved = personRepository.save(person);
        Optional<Person> findByFullNameAndCpf = personRepository.findByFullNameAndCpf(personSaved.getFullName(), personSaved.getCpf());
        Assertions.assertThat(findByFullNameAndCpf).isNotNull();
    }
}

