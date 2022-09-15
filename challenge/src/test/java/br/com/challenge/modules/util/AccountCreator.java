package br.com.challenge.modules.util;

import br.com.challenge.modules.account.entity.Account;
import br.com.challenge.modules.account.requests.AccountCreatePostRequestBody;
import br.com.challenge.modules.account.requests.AccountPutRequestBody;

public class AccountCreator {

    public static Account createAccountToBeSaved(){
        return Account
                .builder()
                .agency("2865")
                .account("306954")
                .balance(3000L)
                .person(PersonCreator.createPersonToBeSaved())
                .build();
    }

    public static Account createAccountToValid(){
        return Account
                .builder()
                .id(1L)
                .agency("2865")
                .account("306954")
                .balance(3000L)
                .person(PersonCreator.createPersonToBeSaved())
                .build();
    }

    public static AccountPutRequestBody createAccountPutRequestBody(){
        return AccountPutRequestBody
                .builder()
                .agency("2865")
                .account("306954")
                .value(1500L)
                .build();
    }

    public static AccountCreatePostRequestBody createAccountCreatePostRequestBody(){
        return AccountCreatePostRequestBody
                .builder()
                .fullName(PersonCreator.createPersonValid().getFullName())
                .cpf(PersonCreator.createPersonValid().getCpf())
                .build();
    }

}
