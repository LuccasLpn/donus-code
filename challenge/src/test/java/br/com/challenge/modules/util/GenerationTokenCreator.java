package br.com.challenge.modules.util;

import br.com.challenge.modules.security.dto.GenerationToken;

public class GenerationTokenCreator {


    public GenerationToken createGenerationToken(){
        return GenerationToken
                .builder()
                .id(1l)
                .fullname("Luccas Pereira Nunes")
                .cpf("48188562823")
                .exp(11L)
                .build();
    }
}
