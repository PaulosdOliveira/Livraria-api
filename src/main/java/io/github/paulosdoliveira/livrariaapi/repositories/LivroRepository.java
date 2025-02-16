package io.github.paulosdoliveira.livrariaapi.repositories;


import io.github.paulosdoliveira.livrariaapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    boolean existsByISBN(String ISBN);
    boolean existsByTitulo(String titulo);

}
