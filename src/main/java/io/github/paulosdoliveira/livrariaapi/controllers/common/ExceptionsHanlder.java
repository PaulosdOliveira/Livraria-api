package io.github.paulosdoliveira.livrariaapi.controllers.common;

import io.github.paulosdoliveira.livrariaapi.exceptions.CompraJaEfetuadaException;
import io.github.paulosdoliveira.livrariaapi.exceptions.EmailDuplicadoEXception;
import io.github.paulosdoliveira.livrariaapi.exceptions.autor.AutorDuplicadoException;
import io.github.paulosdoliveira.livrariaapi.exceptions.LivroDuplicadoException;
import io.github.paulosdoliveira.livrariaapi.exceptions.autor.AutorInativoException;
import io.github.paulosdoliveira.livrariaapi.exceptions.resposta.ErroResposta;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionsHanlder {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(LivroDuplicadoException.class)
    public ErroResposta handlerLivroDuplicadoException(LivroDuplicadoException e)  {
        return new ErroResposta(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AutorDuplicadoException.class)
    public ErroResposta handlerAutorDuplicadoException(AutorDuplicadoException e) {
        return new ErroResposta(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AutorInativoException.class)
    public ErroResposta handlerAutorInativoException(AutorInativoException e) {
        return new ErroResposta(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CompraJaEfetuadaException.class)
    public ErroResposta handlerCompraJaEfetuadaException(CompraJaEfetuadaException e){
        return new ErroResposta(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmailDuplicadoEXception.class)
    public ErroResposta handlerEmailDuplicadoEXception(EmailDuplicadoEXception e){
        return new ErroResposta(e.getMessage());
    }


}
