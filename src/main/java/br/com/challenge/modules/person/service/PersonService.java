package br.com.challenge.modules.person.service;

import br.com.challenge.modules.exception.BadRequestException;
import br.com.challenge.modules.person.entity.Person;
import br.com.challenge.modules.person.mapper.PersonMapper;
import br.com.challenge.modules.person.repository.PersonRepository;
import br.com.challenge.modules.person.requests.PersonPostRequestBody;
import br.com.challenge.modules.person.requests.PersonPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    @Transactional
    public Person save(PersonPostRequestBody personPostRequestBody){
        Person convertDtoToEntity = PersonMapper.INSTANCE.toPerson(personPostRequestBody);
        return personRepository.save(convertDtoToEntity);
    }

    public Person findByFullNameAndCpf(PersonPostRequestBody personPostRequestBody){
        return personRepository.findByFullNameAndCpf(personPostRequestBody.getFullName(),
                personPostRequestBody.getCpf()).orElseThrow(() -> new BadRequestException("Person not Found"));
    }
    public void update(PersonPutRequestBody personPutRequestBody){
        Person personSaved = findByIdOrThrowBadRequestException(personPutRequestBody.getId());
        Person personUpdate = PersonMapper.INSTANCE.toPerson(personPutRequestBody);
        personUpdate.setId(personSaved.getId());
        personRepository.save(personUpdate);
    }
    public void delete(Long id){
        personRepository.delete(findByIdOrThrowBadRequestException(id));
    }
    public Person findByIdOrThrowBadRequestException(Long id){
        return personRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Person not Found"));
    }

    public Page<Person> findAllPageable(Pageable pageable){
        return personRepository.findAll(pageable);
    }

    public List<Person> findAllNonPageable(){
        return personRepository.findAll();
    }


}
