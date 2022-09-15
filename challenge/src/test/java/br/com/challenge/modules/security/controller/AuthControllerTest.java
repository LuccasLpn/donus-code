package br.com.challenge.modules.security.controller;

import br.com.challenge.modules.person.entity.Person;
import br.com.challenge.modules.person.requests.PersonPostRequestBody;
import br.com.challenge.modules.person.service.PersonService;
import br.com.challenge.modules.security.config.JwtUtil;
import br.com.challenge.modules.util.PersonCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

@ExtendWith(SpringExtension.class)
@DisplayName("AuthControllerTest")
class AuthControllerTest {


    @InjectMocks
    private AuthController authController;


    @Mock
    private PersonService personService;

    @Mock
    private JwtUtil jwtUtil = new JwtUtil();
    private HttpServletResponse response = new MockHttpServletResponse();


    @Test
    @DisplayName("Login When SucessFull")
    void Login_When_SucessFull() {
        BDDMockito.when(personService.findByFullNameAndCpf(ArgumentMatchers.any(PersonPostRequestBody.class)))
                .thenReturn(PersonCreator.createPersonValid());
        ResponseEntity<Void> entity = authController.login(PersonCreator.createPersonPostRequestBody(), response);
        Assertions.assertThat(entity.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getHeaders("Authorization")).isNotNull();
    }

    @Test
    @DisplayName("Login When Failed")
    void Login_When_Failed() {
        Person person = PersonCreator.createPersonValid();
        person.setId(null);
        BDDMockito.when(personService.findByFullNameAndCpf(ArgumentMatchers.any(PersonPostRequestBody.class)))
                .thenReturn(person);
        ResponseEntity<Void> entity = authController.login(PersonCreator.createPersonPostRequestBody(), response);
        Assertions.assertThat(entity.getStatusCode()).isNotNull().isEqualTo(HttpStatus.BAD_REQUEST);

    }



}