package br.com.challenge.modules.person.controller;

import br.com.challenge.modules.person.entity.Person;
import br.com.challenge.modules.person.requests.PersonPostRequestBody;
import br.com.challenge.modules.person.service.PersonService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DisplayName("PersonControllerTest")
class PersonControllerTest {

    @InjectMocks
    private PersonController personController;


    @Mock
    private PersonService personService;

    @BeforeEach
    void setUp(){
        PageImpl<Person> personPage = new PageImpl<>(List.of(PersonCreator.createPersonValid()));

        BDDMockito.when(personService.findAllPageable(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(personPage);

        BDDMockito.when(personService.findAllNonPageable())
                .thenReturn(List.of(PersonCreator.createPersonValid()));

        BDDMockito.when(personService.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenReturn(PersonCreator.createPersonValid());

        BDDMockito.when(personService.save(ArgumentMatchers.any(PersonPostRequestBody.class)))
                .thenReturn(PersonCreator.createPersonValid());

        BDDMockito.when(personService.findByFullNameAndCpf(ArgumentMatchers.any(PersonPostRequestBody.class)))
                .thenReturn(PersonCreator.createPersonValid());

        BDDMockito.doNothing().when(personService).delete(ArgumentMatchers.anyLong());
    }


    @Test
    @DisplayName("Saved Persist Person When Successful")
    void saved_Persist_Person_When_SucessFull() {
        Person expected = PersonCreator.createPersonToBeSaved();
        Person person = personController.save(PersonCreator.createPersonPostRequestBody()).getBody();
        Assertions.assertThat(person).isNotNull();
        Assertions.assertThat(person.getFullName()).isNotNull().isEqualTo(expected.getFullName());
        Assertions.assertThat(person.getCpf()).isNotNull().isEqualTo(expected.getCpf());
    }

    @Test
    @DisplayName("Update Person When Successful")
    void update_Person_When_SucessFull() {
        Assertions.assertThatCode(()-> personController.update(PersonCreator.createPersonPutRequestBody()));
        ResponseEntity<Void> entity = personController.update(PersonCreator.createPersonPutRequestBody());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Delete Remove Person When SuccessFull")
    void delete_Remove_Person_When_SuccessFull() {
        Assertions.assertThatCode(()-> personController.delete(1L)).doesNotThrowAnyException();
        ResponseEntity<Void> entity = personController.delete(1L);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("FindAllNonPageable Return List Of When SuccessFull")
    void findAllNonPageable_Return_List_Of_When_SuccessFull() {
        Person expected = PersonCreator.createPersonValid();
        List<Person> persons = personController.findAllPageable().getBody();
        Assertions.assertThat(persons)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(persons.get(0).getFullName()).isEqualTo(expected.getFullName());

    }

    @Test
    @DisplayName("FindAllPageable Return List Of When SuccessFull")
    void findAllPageable_Return_List_Of_When_SuccessFull() {
        Person expected = PersonCreator.createPersonValid();
        Page<Person> personPage = personController.findAllPageable(PageRequest.of(1,1)).getBody();
        Assertions.assertThat(personPage).isNotNull();
        Assertions.assertThat(personPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(personPage.toList().get(0).getFullName()).isEqualTo(expected.getFullName());
        Assertions.assertThat(personPage.toList().get(0).getFullName()).isEqualTo(expected.getFullName());
    }

    @Test
    @DisplayName("FindById Return Person When SuccessFull")
    void findById_Return_Person_When_SuccessFull() {
        Person expected = PersonCreator.createPersonValid();
        Person person = personController.findByIdOrThrowBadRequestException(expected.getId()).getBody();
        Assertions.assertThat(person).isNotNull();
        Assertions.assertThat(person.getFullName()).isNotNull().isEqualTo(expected.getFullName());
        Assertions.assertThat(person.getCpf()).isNotNull().isEqualTo(expected.getCpf());
    }

}