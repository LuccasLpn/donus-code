package br.com.challenge.modules.security.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class GenerationToken implements Serializable {
    private Long id;
    private Long exp;
    private String fullname;
    private String cpf;
}
