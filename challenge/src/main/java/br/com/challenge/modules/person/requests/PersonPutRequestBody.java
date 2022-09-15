package br.com.challenge.modules.person.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonPutRequestBody {
    @NotNull(message = "Id Cannot Be Null")
    private Long id;
    @NotNull(message = "Name Cannot Be Null")
    private String fullName;
    @NotNull(message = "Cpf Cannot Be Null")
    private String cpf;
    private String authorities;
}
