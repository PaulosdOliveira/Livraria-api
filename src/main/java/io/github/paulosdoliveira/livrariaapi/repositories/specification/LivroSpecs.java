package io.github.paulosdoliveira.livrariaapi.repositories.specification;

import io.github.paulosdoliveira.livrariaapi.model.Livro;
import io.github.paulosdoliveira.livrariaapi.model.enums.GeneroLivro;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class LivroSpecs {


    public static Specification<Livro> conjunction() {
        return (root, query, cb) -> cb.conjunction();
    }

    public static Specification<Livro> tituloLike(String titulo) {
        return ((root, query, cb) -> cb.like(cb.upper(root.get("titulo")),
                "%" + titulo.toUpperCase() + "%"));
    }

    public static Specification<Livro> generoIqual(GeneroLivro genero) {
        return (root, query, cb) -> cb.equal(root.get("genero"),
                genero);
    }

    public static Specification<Livro> anoEqual(Integer ano) {

        return (root, query, cb) ->
                cb.equal(cb.function("extract", Integer.class, root.get("dataPublicacao"), cb.literal("YEAR")), ano);
    }

    public static Specification<Livro> isAtivo() {
        return (root, query, cb)
                -> cb.equal(root.get("ativo"), true);
    }




}
