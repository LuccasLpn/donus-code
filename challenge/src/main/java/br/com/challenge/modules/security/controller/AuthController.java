package br.com.challenge.modules.security.controller;

import br.com.challenge.modules.person.entity.Person;
import br.com.challenge.modules.person.requests.PersonPostRequestBody;
import br.com.challenge.modules.person.service.PersonService;
import br.com.challenge.modules.security.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(path = {"/auth"})
@RequiredArgsConstructor
public class AuthController {

    private final PersonService personService;


    @PostMapping
    public ResponseEntity<Void> login(@RequestBody @Valid PersonPostRequestBody account, HttpServletResponse response){
        Person person = personService.findByFullNameAndCpf(account);
        if (person.getId() != null){
            JwtUtil jwtUtil = new JwtUtil();
            String token = jwtUtil.generationToken(person.getId(), person.getFullName(), person.getCpf());
            response.addHeader("Authorization", "Bearer "+token);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
