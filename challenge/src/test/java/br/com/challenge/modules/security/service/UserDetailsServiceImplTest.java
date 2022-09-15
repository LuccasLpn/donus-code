package br.com.challenge.modules.security.service;

import br.com.challenge.modules.person.entity.Person;
import br.com.challenge.modules.person.repository.PersonRepository;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DisplayName("UserDetailsServiceImplTest")
class UserDetailsServiceImplTest {


    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        BDDMockito.when(personRepository.findByFullName(ArgumentMatchers.anyString()))
                .thenReturn(PersonCreator.createPersonValid());
    }

    @Test
    @DisplayName("loadUserByUsername When SucessFull")
    void loadUserByUsername_When_SucessFull() {
        Person expected = PersonCreator.createPersonValid();
        UserDetails userDetails = userDetailsService.loadUserByUsername("Luccas Pereira Nunes");
        Assertions.assertThat(userDetails).isNotNull();
        Assertions.assertThat(userDetails.getUsername()).isNotNull().isEqualTo(expected.getUsername());
        Assertions.assertThat(userDetails.getAuthorities()).isNotNull().isEqualTo(expected.getAuthorities());
    }

}