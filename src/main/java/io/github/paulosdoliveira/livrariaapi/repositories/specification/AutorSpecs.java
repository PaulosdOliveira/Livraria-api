package io.github.paulosdoliveira.livrariaapi.repositories.specification;

import io.github.paulosdoliveira.livrariaapi.model.Autor;
import org.springframework.data.jpa.domain.Specification;

public class AutorSpecs {

    public static Specification<Autor> nomeLike(String nome) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("nome"))
                , "%" + nome.toUpperCase() + "%");
    }

    public static Specification<Autor> anoEqual(Integer ano) {
        return (root, query, cb)
                -> cb.equal(cb.function("extract", Integer.class, root.get("dataNascimento"), cb.literal("Year"))
                , ano);
    }

    public static Specification<Autor> isAtivo() {
        return (root, query, cb)
                -> cb.equal(root.get("ativo"), true);
    }

    public static Specification<Autor> conjunction() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

}
