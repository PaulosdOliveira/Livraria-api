package io.github.paulosdoliveira.livrariaapi.repositories;

import io.github.paulosdoliveira.livrariaapi.model.Autor;
import io.github.paulosdoliveira.livrariaapi.model.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.Optional;
import java.util.UUID;

public interface FotoRepository extends JpaRepository<Foto, UUID> {

    Optional<Foto> findByAutor(Autor autor);

    @Query("Select f from Foto f  where f.autor.id = :idAutor ")
    Optional<Foto> buscarPorIdAutor(UUID idAutor);




}
