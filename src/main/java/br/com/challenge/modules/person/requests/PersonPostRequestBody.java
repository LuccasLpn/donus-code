package br.com.challenge.modules.person.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonPostRequestBody {

    @NotNull(message = "Name Cannot Be Null")
    private String fullName;
    @NotNull(message = "Cpf Cannot Be Null")
    @NotEmpty(message = "The Cpf cannot be empty")
    private String cpf;
    private String authorities;
}