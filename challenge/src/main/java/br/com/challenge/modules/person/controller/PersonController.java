package br.com.challenge.modules.person.controller;

import br.com.challenge.modules.person.entity.Person;
import br.com.challenge.modules.person.requests.PersonPostRequestBody;
import br.com.challenge.modules.person.requests.PersonPutRequestBody;
import br.com.challenge.modules.person.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = {"/person"})
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @Operation(summary = "Save person in database")
    @PostMapping(path = {"/save"})
    public ResponseEntity<Person> save(@RequestBody @Valid PersonPostRequestBody personPostRequestBody){
        Person personResponse = personService.save(personPostRequestBody);
        return new ResponseEntity<>(personResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Update person in database")
    @PutMapping(path = {"/update"})
    public ResponseEntity<Void> update(@RequestBody @Valid PersonPutRequestBody personPutRequestBody){
        personService.update(personPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = {"/delete/{id}"})
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        personService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Search for person receiving id")
    @GetMapping(path = {"/findById/{id}"})
    public ResponseEntity<Person> findByIdOrThrowBadRequestException(@PathVariable("id") Long id){
        Person personResponse = personService.findByIdOrThrowBadRequestException(id);
        return new ResponseEntity<>(personResponse, HttpStatus.OK);
    }

    @Operation(summary = "Search for person pagination")
    @GetMapping(path = {"/findAllPageable"})
    public ResponseEntity<Page<Person>> findAllPageable(@ParameterObject Pageable pageable){
        Page<Person> personResponse = personService.findAllPageable(pageable);
        return new ResponseEntity<>(personResponse, HttpStatus.OK);
    }

    @Operation(summary = "Search for person non pagination")
    @GetMapping(path = {"/findAllNonPageable"})
    public ResponseEntity<List<Person>> findAllPageable(){
        List<Person> personResponse = personService.findAllNonPageable();
        return new ResponseEntity<>(personResponse, HttpStatus.OK);
    }

}
