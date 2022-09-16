package br.com.challenge.modules.account.controller;

import br.com.challenge.modules.account.entity.Account;
import br.com.challenge.modules.account.requests.AccountCreatePostRequestBody;
import br.com.challenge.modules.account.requests.AccountPutRequestBody;
import br.com.challenge.modules.account.response.BalanceResponse;
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

    @PostMapping(path = {"/create"})
    public ResponseEntity<AccountCreatePostRequestBody> createAccount(@RequestBody @Valid AccountCreatePostRequestBody accountCreatePostRequestBody) {
        AccountCreatePostRequestBody accountResponse = accountService.createAccount(accountCreatePostRequestBody);
        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
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
        JwtUtil jwtUtil = new JwtUtil();
        Claims claims = jwtUtil.getClaims(request.getHeader("Authorization").replace("Bearer ", ""));
        accountService.transfer(accountPutRequestBody, claims.get("fullname").toString(), claims.get("cpf").toString());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping(path = {"/delete/{id}"})
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        accountService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = {"/balance"})
    public ResponseEntity<BalanceResponse> findBalance(HttpServletRequest request){
        JwtUtil jwtUtil = new JwtUtil();
        Claims claims = jwtUtil.getClaims(request.getHeader("Authorization").replace("Bearer ", ""));
        BalanceResponse accountResponse = accountService.findBalance(claims.get("fullname").toString());
        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }

}
