package io.github.paulosdoliveira.livrariaapi.repositories;

import io.github.paulosdoliveira.livrariaapi.model.compras.Compras;
import io.github.paulosdoliveira.livrariaapi.model.Livro;
import io.github.paulosdoliveira.livrariaapi.model.compras.ComprasPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ComprasRepository extends JpaRepository<Compras, ComprasPK> {

    @Query("Select c.id.livro from Compras c where c.id.usuario.id = :idUsuario  ")
    List<Livro> getLivrosComprados(@Param("idUsuario") UUID id);
}
