package io.github.paulosdoliveira.livrariaapi.dto.livro;

import io.github.paulosdoliveira.livrariaapi.model.enums.GeneroLivro;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class LivroNovosDadosDTO extends LivroCadastroDTO {


    public LivroNovosDadosDTO(String titulo, String descricao, GeneroLivro genero, String isbn, LocalDate dataPublicacao, UUID idAutor, MultipartFile arquivo) throws IOException {
        super(titulo, descricao, genero, isbn, dataPublicacao, idAutor, arquivo);
    }
}
