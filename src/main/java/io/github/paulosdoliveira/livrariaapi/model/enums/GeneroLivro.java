package io.github.paulosdoliveira.livrariaapi.model.enums;

import java.util.Arrays;

public enum GeneroLivro {
    DRAMA("DRAMA"),
    ROMANCE("ROMANCE"),
    CIENCIA("CIENCIA"),
    TERROR("TERROR"),
    COMEDIA("COMEDIA"),
    SUSPENSE("SUSPENSE");

    private final String genero;

    GeneroLivro(String genero) {
        this.genero = genero;
    }

    public String getGenero(){
        return this.genero;
    }
    
    public static GeneroLivro valueOfString(String genero){
        GeneroLivro generoLivro = Arrays.stream(values())
                .filter( livro -> livro.genero.equals(genero.toUpperCase()))
                .findFirst()
                .orElse(null);
        return generoLivro;
    }
}
