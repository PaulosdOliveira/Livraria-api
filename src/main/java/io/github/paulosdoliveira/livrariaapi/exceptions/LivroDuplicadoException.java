package io.github.paulosdoliveira.livrariaapi.exceptions;

public class LivroDuplicadoException extends RuntimeException{
    public LivroDuplicadoException(String erro){
        super(erro);
    }
}
