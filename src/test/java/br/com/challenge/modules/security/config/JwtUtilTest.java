package br.com.challenge.modules.security.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JwtUtilTest")
class JwtUtilTest {
    JwtUtil jwtUtil = new JwtUtil();

    @Test
    @DisplayName("Generation Token When SuccessFull")
    void generation_Token_When_SuccessFull() {
        String token = jwtUtil.generationToken(1L, "Luccas Pereira Nunes", "48188562823");
        Assertions.assertThat(token).isNotNull();
    }

    @Test
    @DisplayName("GetClaims Token When SuccessFull")
    void getClaims_Token_When_SuccessFull() {
        String token = jwtUtil.generationToken(1L, "Luccas Pereira Nunes", "48188562823");
        Claims claims = jwtUtil.getClaims(token);
        Assertions.assertThat(claims).isNotNull();
    }
    @Test
    @DisplayName("IsTokenValid When SuccessFull")
    void isTokenValid_When_SuccessFull() {
        String token = jwtUtil.generationToken(1L, "Luccas Pereira Nunes", "48188562823");
        try {
            Boolean tokenValid = jwtUtil.isTokenValid(token);
            Assertions.assertThat(tokenValid).isTrue();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("GetUsername Return When SuccessFull")
    void getUsername_Return_When_SuccessFull() {
        String token = jwtUtil.generationToken(1L, "Luccas Pereira Nunes", "48188562823");
        String username = jwtUtil.getUsername(token);
        Assertions.assertThat(username).isNotNull();
    }
}