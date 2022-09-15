package br.com.challenge.modules.person.requests;

import br.com.challenge.modules.person.entity.Person;
import br.com.challenge.modules.person.repository.PersonRepository;
import br.com.challenge.modules.util.PersonCreator;
import io.micrometer.core.instrument.config.validate.ValidationException;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.*;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class PersonPostRequestBodyTest {

    @Test
    void getFullName() {
        PersonPostRequestBody person = PersonCreator.createPersonPostRequestBody();
        person.setFullName(null);
        Assertions.assertThat(person.getFullName()).isNull();
    }

    @Test
    void getCpf() {
        PersonPostRequestBody person = PersonCreator.createPersonPostRequestBody();
        person.setCpf(null);
        Assertions.assertThat(person.getCpf()).isNull();
    }

    @Test
    void getAuthorities() {
        PersonPostRequestBody person = PersonCreator.createPersonPostRequestBody();
        person.setAuthorities(null);
        Assertions.assertThat(person.getAuthorities()).isNull();
    }
}