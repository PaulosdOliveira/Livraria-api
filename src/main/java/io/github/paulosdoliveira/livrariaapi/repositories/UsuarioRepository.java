package io.github.paulosdoliveira.livrariaapi.repositories;

import io.github.paulosdoliveira.livrariaapi.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuarios, UUID> {
    Usuarios findByEmail(String email);
    boolean existsByEmail(String email);

}
