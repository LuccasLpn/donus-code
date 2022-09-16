package br.com.challenge.modules.person.service;

import br.com.challenge.modules.person.entity.Person;
import br.com.challenge.modules.person.repository.PersonRepository;
import br.com.challenge.modules.person.requests.PersonPostRequestBody;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("PersonServiceTest")
class PersonServiceTest {


    @InjectMocks
    private PersonService personService;


    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    void setUp(){
        PageImpl<Person> personPage = new PageImpl<>(List.of(PersonCreator.createPersonValid()));

        BDDMockito.when(personRepository.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(personPage);

        BDDMockito.when(personRepository.findAll())
                .thenReturn(List.of(PersonCreator.createPersonValid()));

        BDDMockito.when(personRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(PersonCreator.createPersonValid()));

        BDDMockito.when(personRepository.save(ArgumentMatchers.any(Person.class)))
                .thenReturn(PersonCreator.createPersonValid());

        BDDMockito.when(personRepository.findByFullNameAndCpf(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                        .thenReturn(Optional.ofNullable(PersonCreator.createPersonValid()));

        BDDMockito.doNothing().when(personRepository).delete(ArgumentMatchers.any(Person.class));
    }

    @Test
    @DisplayName("Saved Persist Person When Successful")
    void saved_Persist_Person_When_SucessFull() {
        Person expected = PersonCreator.createPersonValid();
        Person person = personService.save(PersonCreator.createPersonPostRequestBody());
        Assertions.assertThat(person).isNotNull();
        Assertions.assertThat(person.getCpf()).isNotNull().isEqualTo(expected.getCpf());
        Assertions.assertThat(person.getFullName()).isNotNull().isEqualTo(expected.getFullName());
        Assertions.assertThat(person.getAuthorities()).isNotNull().isEqualTo(expected.getAuthorities());
    }

    @Test
    @DisplayName("Update Person When Successful")
    void update_Person_When_SucessFull() {
        Assertions.assertThatCode(()-> personService.update(PersonCreator.createPersonPutRequestBody()));
    }

    @Test
    @DisplayName("Delete Remove Person When SuccessFull")
    void delete_Remove_Person_When_SuccessFull() {
        Assertions.assertThatCode(()-> personService.delete(1L)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("FindAllNonPageable Return List Of When SuccessFull")
    void findAllNonPageable_Return_List_Of_When_SuccessFull() {
        String expectedName = PersonCreator.createPersonValid().getFullName();
        List<Person> persons = personService.findAllNonPageable();
        Assertions.assertThat(persons)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(persons.get(0).getFullName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("FindAllPageable Return List Of When SuccessFull")
    void findAllPageable_Return_List_Of_When_SuccessFull() {
        String expectedName = PersonCreator.createPersonValid().getFullName();
        Page<Person> personPage = personService.findAllPageable(PageRequest.of(1, 1));
        Assertions.assertThat(personPage).isNotNull();
        Assertions.assertThat(personPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(personPage.toList().get(0).getFullName()).isEqualTo(expectedName);
        Assertions.assertThat(personPage.toList().get(0).getFullName()).isEqualTo(expectedName);
    }


    @Test
    @DisplayName("FindByFullNameAndCpf Return List Of When SuccessFull")
    void findByFullNameAndCpf_Return_List_Of_When_SuccessFull() {
        PersonPostRequestBody personPostRequestBody = PersonCreator.createPersonPostRequestBody();
        Person findByFullnameAndCpf = personService.findByFullNameAndCpf(personPostRequestBody);
        Assertions.assertThat(findByFullnameAndCpf).isNotNull();
        Assertions.assertThat(findByFullnameAndCpf.getFullName()).isNotNull().isEqualTo(personPostRequestBody.getFullName());
        Assertions.assertThat(findByFullnameAndCpf.getCpf()).isNotNull().isEqualTo(personPostRequestBody.getCpf());
    }



}