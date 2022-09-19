package br.com.challenge.modules.security.config;

import br.com.challenge.modules.security.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    public final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService);
        http.csrf().disable().authorizeRequests()
                .mvcMatchers(HttpMethod.POST, "/auth").permitAll()
                .mvcMatchers( "/actuator/**").permitAll()
                .mvcMatchers(HttpMethod.POST, "/account/create").permitAll()
                .mvcMatchers(HttpMethod.DELETE, "/person/delete/**").hasAuthority("ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/account/delete/**").hasAuthority("ADMIN")
                .mvcMatchers("/swagger-ui/**").permitAll()
                .mvcMatchers("/v3/api-docs/**").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(new JwtAuthorizationFilter(jwtUtil,userService), UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
