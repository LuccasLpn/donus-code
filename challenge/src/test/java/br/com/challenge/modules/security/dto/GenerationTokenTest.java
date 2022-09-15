package br.com.challenge.modules.security.dto;

import br.com.challenge.modules.util.GenerationTokenCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GenerationTokenTest {

    GenerationToken expected = new GenerationTokenCreator().createGenerationToken();

    @Test
    void getId() {
        Assertions.assertThat(expected.getId()).isNotNull();
    }

    @Test
    void getExp() {
        Assertions.assertThat(expected.getExp()).isNotNull();
    }

    @Test
    void getFullname() {
        Assertions.assertThat(expected.getFullname()).isNotNull();
    }

    @Test
    void getCpf() {
        Assertions.assertThat(expected.getCpf()).isNotNull();
    }
}