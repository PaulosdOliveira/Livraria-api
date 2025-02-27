package io.github.paulosdoliveira.livrariaapi.config;

import io.github.paulosdoliveira.livrariaapi.config.filter.JwtFilter;
import io.github.paulosdoliveira.livrariaapi.jwt.JwtService;
import io.github.paulosdoliveira.livrariaapi.services.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class SecurityConfiguration {

    @Bean
    public JwtFilter jwtFilter(JwtService service, UsuarioService usuarioService) {
        return new JwtFilter(service, usuarioService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter filter) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors( cors -> cors.configure(http))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.POST, "/usuario/**").permitAll();
                    auth.requestMatchers("/autores/foto/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

@Bean
    public CorsConfigurationSource corsConfigurationSource(){
    CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
    UrlBasedCorsConfigurationSource cors = new UrlBasedCorsConfigurationSource();
    cors.registerCorsConfiguration("/**", config);
    return cors;
}

}
