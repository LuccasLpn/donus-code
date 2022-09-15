package br.com.challenge.modules.account.mapper;

import br.com.challenge.modules.account.requests.AccountCreatePostRequestBody;
import br.com.challenge.modules.person.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {

    public static final AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    public abstract Person toPerson(AccountCreatePostRequestBody accountCreatePostRequestBody);
}
