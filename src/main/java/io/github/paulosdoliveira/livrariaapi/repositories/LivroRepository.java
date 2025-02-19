package io.github.paulosdoliveira.livrariaapi.repositories;


import io.github.paulosdoliveira.livrariaapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID>, JpaSpecificationExecutor<Livro> {
    boolean existsByISBN(String ISBN);
    boolean existsByTitulo(String titulo);

    @Transactional
    @Modifying
    @Query("Update Livro l set l.ativo = false where l.autor.id = :idAutor ")
    void deletarEmCascata(@Param("idAutor") UUID idAutor);



}
