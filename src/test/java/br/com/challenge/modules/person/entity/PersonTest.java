package br.com.challenge.modules.person.entity;

import br.com.challenge.modules.person.repository.PersonRepository;
import br.com.challenge.modules.security.service.UserDetailsServiceImpl;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("UserDetailsServiceImplTest")
class PersonTest {

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
    void getAuthorities() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("Luccas Pereira Nunes");
        Assertions.assertThat(userDetails).isNotNull();
        Assertions.assertThat(userDetails.getAuthorities()).isNotNull();
    }

    @Test
    void getPassword() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("Luccas Pereira Nunes");
        Assertions.assertThat(userDetails).isNotNull();
        Assertions.assertThat(userDetails.getPassword()).isNotNull();
    }

    @Test
    void getUsername() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("Luccas Pereira Nunes");
        Assertions.assertThat(userDetails).isNotNull();
        Assertions.assertThat(userDetails.getUsername()).isNotNull();
    }

    @Test
    void isAccountNonExpired() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("Luccas Pereira Nunes");
        Assertions.assertThat(userDetails).isNotNull();
        Assertions.assertThat(userDetails.isAccountNonExpired()).isNotNull();
    }

    @Test
    void isAccountNonLocked() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("Luccas Pereira Nunes");
        Assertions.assertThat(userDetails).isNotNull();
        Assertions.assertThat(userDetails.isAccountNonLocked()).isNotNull();
    }

    @Test
    void isCredentialsNonExpired() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("Luccas Pereira Nunes");
        Assertions.assertThat(userDetails).isNotNull();
        Assertions.assertThat(userDetails.isCredentialsNonExpired()).isNotNull();
    }

    @Test
    void isEnabled() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("Luccas Pereira Nunes");
        Assertions.assertThat(userDetails).isNotNull();
        Assertions.assertThat(userDetails.isEnabled()).isNotNull();
    }
}