package io.github.paulosdoliveira.livrariaapi.repositories;

import io.github.paulosdoliveira.livrariaapi.model.Compras;
import io.github.paulosdoliveira.livrariaapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ComprasRepository extends JpaRepository<Compras, UUID> {

    @Query("Select livro from Compras c join c.usuario u on u.id = :id ")
    List<Livro> getLivrosComprados(@Param("id") UUID id);
}
