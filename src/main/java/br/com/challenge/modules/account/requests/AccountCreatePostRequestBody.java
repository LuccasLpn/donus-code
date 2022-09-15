package br.com.challenge.modules.account.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreatePostRequestBody {
    @NotNull(message = "Name Cannot Be Null")
    private String fullName;
    @NotNull(message = "Cpf Cannot Be Null")
    private String cpf;
}
