package io.github.paulosdoliveira.livrariaapi.repositories;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import java.time.LocalDate;
import org.springframework.util.StringUtils;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import io.github.paulosdoliveira.livrariaapi.model.Autor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import static io.github.paulosdoliveira.livrariaapi.repositories.specification.AutorSpecs.*;

public interface AutorRepository extends JpaRepository<Autor, UUID>, JpaSpecificationExecutor<Autor> {

    boolean existsByNomeAndDataNascimento(String nome, LocalDate dataNascimento);

    Optional<Autor> findByIdAndAtivo(UUID id, boolean ativo);

    @Query("Select a from Autor a where upper(a.nome) like :letra% order by nome")
    List<Autor> buscaLetrada(@Param("letra") String letra);

    @Transactional
    @Modifying
    @Query("Update Autor set livrosVendidos = livrosVendidos + 1 where id = :id")
    void vendeuLivro(@Param("id") UUID id);

    default List<Autor> buscaFiltrada(String nome, Integer ano) {
        Specification<Autor> specs = isAtivo();
        if (StringUtils.hasText(nome)) {
            specs = specs.and(nomeLike(nome));
        }
        if (ano != null && ano.toString().length() == 4) {
            specs = specs.and(anoEqual(ano));
        }
        return findAll(specs);
    }
}
