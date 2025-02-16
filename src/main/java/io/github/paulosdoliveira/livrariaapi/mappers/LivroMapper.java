package io.github.paulosdoliveira.livrariaapi.mappers;

import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroCadastroDTO;
import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroCartaoDTO;
import io.github.paulosdoliveira.livrariaapi.model.Livro;
import io.github.paulosdoliveira.livrariaapi.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    @Autowired
    private AutorService autorService;

    public Livro toEntity(LivroCadastroDTO dto) {
        var livro = new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setDescricao(dto.getDescricao());
        livro.setISBN(dto.getISBN());
        livro.setAutor(autorService.buscarPorId(dto.getIdAutor()));
        livro.setDataPublicacao(dto.getDataPublicacao());
        return livro;
    }

    public LivroCadastroDTO todto(Livro livro) {
        var dto = new LivroCadastroDTO();
        dto.setTitulo(livro.getTitulo());
        dto.setDescricao(livro.getDescricao());
        dto.setISBN(livro.getISBN());
        dto.setIdAutor(livro.getAutor().getId());
        dto.setDataPublicacao(livro.getDataPublicacao());
        return dto;
    }

    public LivroCartaoDTO toCartao(Livro livro) {
        var cartaoDTO = new LivroCartaoDTO();
        cartaoDTO.setTitulo(livro.getTitulo());
        cartaoDTO.setAutor(livro.getAutor());
        cartaoDTO.setDataPublicacao(livro.getDataPublicacao());
        return cartaoDTO;
    }
}
