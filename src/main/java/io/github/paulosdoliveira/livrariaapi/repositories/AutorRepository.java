package io.github.paulosdoliveira.livrariaapi.repositories;

import io.github.paulosdoliveira.livrariaapi.model.Autor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import static io.github.paulosdoliveira.livrariaapi.repositories.specification.AutorSpecs.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID>, JpaSpecificationExecutor<Autor> {

    boolean existsByNomeAndDataNascimento(String nome, LocalDate dataNascimento);

    default List<Autor> buscaFiltrada(String nome, Integer ano) {
        Specification<Autor> specs = conjunction();
        if (StringUtils.hasText(nome)) {
            specs = specs.and(nomeLike(nome));
        }
        if(ano != null && ano.toString().length() == 4){
            specs = specs.and(anoEqual(ano));
        }
        return findAll(specs);
    }
}
