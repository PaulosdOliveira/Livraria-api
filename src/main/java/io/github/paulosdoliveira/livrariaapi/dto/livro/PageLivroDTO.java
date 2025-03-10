package io.github.paulosdoliveira.livrariaapi.dto.livro;

import java.util.List;

public record PageLivroDTO(List<LivroCartaoDTO> lista, short qtdPaginas) {
}
