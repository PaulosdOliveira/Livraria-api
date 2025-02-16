package io.github.paulosdoliveira.livrariaapi.exceptions;

public class AutorDuplicadoException extends RuntimeException {
    public AutorDuplicadoException(String erro) {
        super(erro);
    }
}
