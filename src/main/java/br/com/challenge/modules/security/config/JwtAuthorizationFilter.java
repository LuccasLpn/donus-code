package br.com.challenge.modules.security.config;

import br.com.challenge.modules.security.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@RequiredArgsConstructor
@AllArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private JwtUtil jwtUtil;

    private final UserDetailsServiceImpl userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        request.setAttribute("Authorization", "");
        String authorization = request.getHeader("Authorization");
        if(authorization != null && authorization.startsWith("Bearer ")){
            UsernamePasswordAuthenticationToken authentication = getAuthentication(authorization);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String authorization) throws JsonProcessingException {
        String token = authorization.substring(7);
        if (jwtUtil.isTokenValid(token)){
            var username = jwtUtil.getUsername(token);
            UserDetails user = userService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
        }
        throw new UsernameNotFoundException("Token Invalid");
    }
}
