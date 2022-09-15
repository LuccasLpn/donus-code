package br.com.challenge.modules.account.controller;

import br.com.challenge.modules.account.requests.AccountCreatePostRequestBody;
import br.com.challenge.modules.account.requests.AccountPutRequestBody;
import br.com.challenge.modules.account.service.AccountService;
import br.com.challenge.modules.security.config.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(path = {"/account"})
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final JwtUtil jwtUtil;


    @PostMapping(path = {"/create"})
    public ResponseEntity<AccountCreatePostRequestBody> createAccount(@RequestBody @Valid AccountCreatePostRequestBody accountCreatePostRequestBody) {
        AccountCreatePostRequestBody accountResponse = accountService.createAccount(accountCreatePostRequestBody);
        return new ResponseEntity<>(accountResponse, HttpStatus.CREATED);
    }

    @PostMapping(path = {"/deposit"})
    public ResponseEntity<AccountPutRequestBody> deposit(@RequestBody @Valid AccountPutRequestBody accountPutRequestBody) {
        AccountPutRequestBody accountResponse = accountService.deposit(accountPutRequestBody);
        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }

    @PutMapping(path = {"/withdraw"})
    public ResponseEntity<AccountPutRequestBody> withdraw(@RequestBody @Valid AccountPutRequestBody accountPutRequestBody) {
        AccountPutRequestBody accountResponse = accountService.withdraw(accountPutRequestBody);
        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }

    @PutMapping(path = {"/transfer"})
    public ResponseEntity<Void> transfer(@RequestBody @Valid AccountPutRequestBody accountPutRequestBody, HttpServletRequest request) {
        Claims claims = jwtUtil.getClaims(request.getHeader("Authorization").replace("Bearer ", ""));
        accountService.transfer(accountPutRequestBody, claims.get("fullname").toString(), claims.get("cpf").toString());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
