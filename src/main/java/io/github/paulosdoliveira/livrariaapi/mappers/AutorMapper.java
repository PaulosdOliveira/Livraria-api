package io.github.paulosdoliveira.livrariaapi.mappers;

import io.github.paulosdoliveira.livrariaapi.dto.livro.autor.AutorDTO;
import io.github.paulosdoliveira.livrariaapi.model.Autor;
import org.springframework.stereotype.Component;

@Component
public class AutorMapper {

    public Autor toEntity(AutorDTO dto) {
        var autor = new Autor();
        autor.setNome(dto.getNome());
        autor.setDataNascimento(dto.getDataNascimento());
        autor.setBreveDescricao(dto.getBreveDescricao());
        autor.setUrlFoto(dto.getUrlFoto());
        autor.setAtivo(true);
        autor.setLivrosVendidos(0L);
        return autor;
    }

    public AutorDTO toDTO(Autor entity) {
        var dto = new AutorDTO();
        dto.setNome(entity.getNome());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setBreveDescricao(entity.getBreveDescricao());
        dto.setUrlFoto(entity.getUrlFoto());
        return dto;
    }
}
