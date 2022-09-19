package br.com.challenge.modules.person.mapper;

import br.com.challenge.modules.person.entity.Person;
import br.com.challenge.modules.person.requests.PersonPostRequestBody;
import br.com.challenge.modules.person.requests.PersonPutRequestBody;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-18T20:41:58-0300",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17 (Oracle Corporation)"
)
@Component
public class PersonMapperImpl extends PersonMapper {

    @Override
    public Person toPerson(PersonPostRequestBody personPostRequestBody) {
        if ( personPostRequestBody == null ) {
            return null;
        }

        Person.PersonBuilder person = Person.builder();

        person.id( personPostRequestBody.getId() );
        person.fullName( personPostRequestBody.getFullName() );
        person.cpf( personPostRequestBody.getCpf() );
        person.authorities( personPostRequestBody.getAuthorities() );

        return person.build();
    }

    @Override
    public Person toPerson(PersonPutRequestBody personPutRequestBody) {
        if ( personPutRequestBody == null ) {
            return null;
        }

        Person.PersonBuilder person = Person.builder();

        person.id( personPutRequestBody.getId() );
        person.fullName( personPutRequestBody.getFullName() );
        person.cpf( personPutRequestBody.getCpf() );
        person.authorities( personPutRequestBody.getAuthorities() );

        return person.build();
    }
}
