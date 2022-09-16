package br.com.challenge.modules.person.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Long id;
    @NotNull(message = "Name Cannot Be Null")
    private String fullName;
    @NotNull(message = "Cpf Cannot Be Null")
    @NotEmpty(message = "The Cpf cannot be empty")
    private String cpf;
    @JsonIgnore
    private String authorities;
}