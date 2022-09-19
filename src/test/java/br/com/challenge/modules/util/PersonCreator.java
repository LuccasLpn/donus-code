package br.com.challenge.modules.util;

import br.com.challenge.modules.person.entity.Person;
import br.com.challenge.modules.person.requests.PersonPostRequestBody;
import br.com.challenge.modules.person.requests.PersonPutRequestBody;

public class PersonCreator {


    public static Person createPersonToBeSaved(){
        return Person
                .builder()
                .fullName("Luccas Pereira Nunes")
                .cpf("48188562823")
                .authorities("USER")
                .build();
    }

    public static Person createPersonToBeSaved2(){
        return Person
                .builder()
                .fullName("Luccas Pereira")
                .cpf("48188562822")
                .authorities("USER")
                .build();
    }

    public static Person createPersonValid(){
        return Person
                .builder()
                .id(1L)
                .fullName("Luccas Pereira Nunes")
                .cpf("48188562823")
                .authorities("USER")
                .build();
    }

    public static PersonPostRequestBody createPersonPostRequestBody(){
        return PersonPostRequestBody
                .builder()
                .fullName(PersonCreator.createPersonToBeSaved().getFullName())
                .cpf(PersonCreator.createPersonToBeSaved().getCpf())
                .authorities(PersonCreator.createPersonToBeSaved().getAuthorities().toString())
                .build();
    }

    public static PersonPutRequestBody createPersonPutRequestBody(){
        return PersonPutRequestBody.builder()
                .id(PersonCreator.createPersonValid().getId())
                .fullName(PersonCreator.createPersonValid().getFullName())
                .cpf(PersonCreator.createPersonValid().getCpf())
                .authorities(PersonCreator.createPersonValid().getAuthorities().toString())
                .build();
    }
}
