package io.github.paulosdoliveira.livrariaapi.jwt;

import io.github.paulosdoliveira.livrariaapi.exceptions.InvalidTokenException;
import io.github.paulosdoliveira.livrariaapi.model.Usuarios;
import io.github.paulosdoliveira.livrariaapi.model.token.AccessToken;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarException;

@Service
public class JwtService {

    @Autowired
    private SecretKeyGenerator secretKeyGenerator;

    public AccessToken gerarToken(Usuarios usuario) {
        SecretKey secretKey = secretKeyGenerator.getKey();

        var token = Jwts.builder()
                .signWith(secretKey)
                .subject(usuario.getEmail())
                .expiration(expiracao())
                .claims(gerarClaims(usuario))
                .compact();
        return new AccessToken(token);
    }


    private Date expiracao() {
        var TEMPO_EXPIRACAO = 60;
        var now = LocalDateTime.now().plusMinutes(TEMPO_EXPIRACAO);
        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    }

    private Map<String, Object> gerarClaims(Usuarios usuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("nome", usuario.getNome());
        return claims;
    }

    public String getEmailFromToken(String token) {
        try {
            String email = Jwts.parser()
                    .verifyWith(secretKeyGenerator.getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
            return email;
        } catch (JwtException e) {
            throw new InvalidTokenException(e.getMessage());
        }

    }

}
