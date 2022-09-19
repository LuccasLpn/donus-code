package br.com.challenge.modules.account.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransferPutRequestBody {
    private AccountPutRequestBody accountDestiny;
    private String agency;
    private String account;
}
