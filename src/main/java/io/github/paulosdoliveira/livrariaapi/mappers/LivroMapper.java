package io.github.paulosdoliveira.livrariaapi.mappers;

import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroCadastroDTO;
import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroCartaoDTO;
import io.github.paulosdoliveira.livrariaapi.model.Livro;
import io.github.paulosdoliveira.livrariaapi.model.Usuarios;
import io.github.paulosdoliveira.livrariaapi.model.compras.ComprasPK;
import io.github.paulosdoliveira.livrariaapi.repositories.ComprasRepository;
import io.github.paulosdoliveira.livrariaapi.services.ComprasService;
import io.github.paulosdoliveira.livrariaapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LivroMapper {



    @Autowired
    private ComprasRepository comprasRepository;

    private String URLBase = "http://localhost:8080/livros/foto/";


    public Livro toEntity(LivroCadastroDTO dto) {
        var livro = new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setPreco(dto.getPreco());
        livro.setGenero(dto.getGenero());
        livro.setDescricao(dto.getDescricao());
        livro.setISBN(dto.getISBN());
        livro.setAtivo(true);
        livro.setDataPublicacao(dto.getDataPublicacao());
        livro.setVendas(0L);
        livro.setImagem(dto.getImagem());
        return livro;
    }

    public LivroCartaoDTO toCartao(Livro livro, Usuarios UsuarioLogado) {
        var cartaoDTO = new LivroCartaoDTO();
        cartaoDTO.setTitulo(livro.getTitulo());
        cartaoDTO.setDescricao(livro.getDescricao());
        cartaoDTO.setGenero(livro.getGenero());
        cartaoDTO.setPreco(String.format("%.2f", livro.getPreco()).replace(".", ",") + "$");
        cartaoDTO.setNomeAutor(livro.getAutor().getNome());
        cartaoDTO.setDataPublicacao(livro.getDataPublicacao());
        cartaoDTO.setVendas(livro.getVendas());
        cartaoDTO.setUrlImagem(URLBase + livro.getId());
        cartaoDTO.setComprado(comprasRepository.existsById(new ComprasPK(UsuarioLogado, livro)));
        return cartaoDTO;
    }
}
