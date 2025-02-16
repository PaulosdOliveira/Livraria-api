package io.github.paulosdoliveira.livrariaapi.exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String erro){
        super(erro);
    }
}
