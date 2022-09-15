package br.com.challenge.modules.person.mapper;

import br.com.challenge.modules.person.entity.Person;
import br.com.challenge.modules.person.requests.PersonPostRequestBody;
import br.com.challenge.modules.person.requests.PersonPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class PersonMapper {

    public static final PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    public abstract Person toPerson(PersonPostRequestBody personPostRequestBody);
    public abstract Person toPerson(PersonPutRequestBody personPutRequestBody);
}
