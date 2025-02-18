package io.github.paulosdoliveira.livrariaapi.repositories;


import io.github.paulosdoliveira.livrariaapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID>, JpaSpecificationExecutor<Livro> {



    boolean existsByISBN(String ISBN);
    boolean existsByTitulo(String titulo);



}
