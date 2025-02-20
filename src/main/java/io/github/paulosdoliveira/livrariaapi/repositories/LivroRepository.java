package io.github.paulosdoliveira.livrariaapi.repositories;


import io.github.paulosdoliveira.livrariaapi.model.Livro;
import io.github.paulosdoliveira.livrariaapi.model.enums.GeneroLivro;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import static io.github.paulosdoliveira.livrariaapi.repositories.specification.LivroSpecs.*;


import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID>, JpaSpecificationExecutor<Livro> {
    boolean existsByISBN(String ISBN);

    boolean existsByTitulo(String titulo);

    @Transactional
    @Modifying
    @Query("Update Livro l set l.ativo = false where l.autor.id = :idAutor ")
    void deletarEmCascata(@Param("idAutor") UUID idAutor);

    default List<Livro> buscaFiltrada(String titulo, GeneroLivro genero, Integer ano, boolean maisAntigo) {
        Specification<Livro> specs = isAtivo();

        if (StringUtils.hasText(titulo)) {
            specs = specs.and(tituloLike(titulo));
        }
        if (genero != null) {
            specs = specs.and(generoIqual(genero));
        }
        if (ano != null) {
            specs = specs.and(anoEqual(ano));
        }
        Sort ordem = Sort.by("vendas").descending();
        if (!maisAntigo) {
            ordem.and(Sort.by("dataPostagem").descending());
        }else{
            ordem.and(Sort.by("dataPostagem").ascending());
        }

        return findAll(specs, ordem);
    }


}
