package br.com.challenge.modules.security.service;

import br.com.challenge.modules.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PersonRepository repository;

    @Override
    public UserDetails loadUserByUsername(String fullname){
      return Optional.ofNullable(repository.findByFullName(fullname))
              .orElseThrow(() -> new UsernameNotFoundException("Cpf Not Found " + fullname));
    }
}
