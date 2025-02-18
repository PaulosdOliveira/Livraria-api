package io.github.paulosdoliveira.livrariaapi.config.filter;

import io.github.paulosdoliveira.livrariaapi.exceptions.InvalidTokenException;
import io.github.paulosdoliveira.livrariaapi.jwt.JwtService;
import io.github.paulosdoliveira.livrariaapi.model.Usuarios;
import io.github.paulosdoliveira.livrariaapi.services.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService service;

    @Autowired
    private UsuarioService usuarioService;

    public JwtFilter() {
    }

    public JwtFilter(JwtService service, UsuarioService usuarioService) {
        this.service = service;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);

        if (token != null) {
            try {
                String email = service.getEmailFromToken(token);
                Usuarios usuario = usuarioService.findByEmail(email);
                logarUsuario(usuario);
            } catch (InvalidTokenException e) {
                System.out.println("Token inválido: " + e);
            } catch (Exception e) {
                System.out.println("Erro: " + e);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String[] tokenDividido = authHeader.split(" ");
            if (tokenDividido.length == 2) return tokenDividido[1];
        }
        return null;
    }

    private void logarUsuario(Usuarios usuario) {
        UserDetails userDetails = User.withUsername(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(usuario.getPerfil().toString())
                .build();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, "", userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public boolean shouldNotFilter(HttpServletRequest request){
        return request.getRequestURI().contains("/usuarios");
    }
}
