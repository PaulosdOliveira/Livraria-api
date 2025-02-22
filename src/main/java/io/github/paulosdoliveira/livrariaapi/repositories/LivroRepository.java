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
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static io.github.paulosdoliveira.livrariaapi.repositories.specification.LivroSpecs.*;

public interface LivroRepository extends JpaRepository<Livro, UUID>, JpaSpecificationExecutor<Livro> {


    Optional<Livro> findByTitulo(String titulo);
    Optional<Livro> findByISBN(String isbn);

    @Transactional
    @Modifying
    @Query("Update Livro l set l.ativo = false where l.autor.id = :idAutor ")
    void deletarEmCascata(@Param("idAutor") UUID idAutor);


    default List<Livro> buscaFiltrada(String titulo, GeneroLivro genero, Integer ano, boolean maisAntigo) {

        Specification<Livro> specs = isAtivo();
        Sort ordem = Sort.by("vendas").descending();

        if (StringUtils.hasText(titulo)) {
            specs = specs.and(tituloLike(titulo));
        }
        if (genero != null) {
            specs = specs.and(generoIqual(genero));
        }
        if (ano != null) {
            specs = specs.and(anoEqual(ano));
        }
        if (!maisAntigo) {
            ordem.and(Sort.by("dataPostagem").descending());
        } else {
            ordem.and(Sort.by("dataPostagem").ascending());
        }

        return findAll(specs, ordem);
    }

    @Query("Select l.imagem from Livro l where l.id = :idLivro")
    byte[] buscarImagem(@Param("idLivro") UUID idLivro);
}
