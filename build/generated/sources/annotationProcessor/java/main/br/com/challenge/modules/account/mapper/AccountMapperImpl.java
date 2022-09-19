package br.com.challenge.modules.account.mapper;

import br.com.challenge.modules.account.requests.AccountCreatePostRequestBody;
import br.com.challenge.modules.person.entity.Person;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-18T20:41:58-0300",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl extends AccountMapper {

    @Override
    public Person toPerson(AccountCreatePostRequestBody accountCreatePostRequestBody) {
        if ( accountCreatePostRequestBody == null ) {
            return null;
        }

        Person.PersonBuilder person = Person.builder();

        person.fullName( accountCreatePostRequestBody.getFullName() );
        person.cpf( accountCreatePostRequestBody.getCpf() );

        return person.build();
    }
}
