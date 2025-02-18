package io.github.paulosdoliveira.livrariaapi.controllers.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.paulosdoliveira.livrariaapi.exceptions.AutorDuplicadoException;
import io.github.paulosdoliveira.livrariaapi.exceptions.InvalidTokenException;
import io.github.paulosdoliveira.livrariaapi.exceptions.LivroDuplicadoException;
import io.github.paulosdoliveira.livrariaapi.exceptions.resposta.ErroResposta;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionsHanlder {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(LivroDuplicadoException.class)
    public ErroResposta handlerLivroDuplicadoException(LivroDuplicadoException e) throws JsonProcessingException {
        return new ErroResposta(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AutorDuplicadoException.class)
    public ErroResposta handlerAutorDuplicadoException(AutorDuplicadoException e) {
        return new ErroResposta(e.getMessage());
    }




}
