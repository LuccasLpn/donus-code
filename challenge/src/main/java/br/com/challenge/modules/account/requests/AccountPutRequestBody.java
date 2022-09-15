package br.com.challenge.modules.account.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountPutRequestBody {
    private String agency;
    private String account;
    private Long value;
}
