package io.github.paulosdoliveira.livrariaapi.dto.livro.compras;

import java.util.UUID;

public record ComprasDTO(UUID idUsuario, UUID idLivro) {
}
