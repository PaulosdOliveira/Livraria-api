package io.github.paulosdoliveira.livrariaapi.exceptions.autor;

public class AutorDuplicadoException extends RuntimeException {
    public AutorDuplicadoException(String erro) {
        super(erro);
    }
}
