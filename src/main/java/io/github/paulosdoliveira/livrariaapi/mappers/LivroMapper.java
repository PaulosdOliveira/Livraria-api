package io.github.paulosdoliveira.livrariaapi.mappers;

import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroCadastroDTO;
import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroCartaoDTO;
import io.github.paulosdoliveira.livrariaapi.model.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    private  String URLBase = "http://localhost:8080/livro/";

    public Livro toEntity(LivroCadastroDTO dto) {
        var livro = new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setGenero(dto.getGenero());
        livro.setDescricao(dto.getDescricao());
        livro.setISBN(dto.getISBN());
        livro.setAtivo(true);
        livro.setDataPublicacao(dto.getDataPublicacao());
        livro.setVendas(0L);
        livro.setImagem(dto.getImagem());
        return livro;
    }

    public LivroCartaoDTO toCartao(Livro livro) {
        var cartaoDTO = new LivroCartaoDTO();
        cartaoDTO.setTitulo(livro.getTitulo());
        cartaoDTO.setDescricao(livro.getDescricao());
        cartaoDTO.setGenero(livro.getGenero());
        cartaoDTO.setNomeAutor(livro.getAutor().getNome());
        cartaoDTO.setDataPublicacao(livro.getDataPublicacao());
        cartaoDTO.setVendas(livro.getVendas());
        cartaoDTO.setUrlImagem(URLBase + livro.getId());
        return cartaoDTO;
    }
}
