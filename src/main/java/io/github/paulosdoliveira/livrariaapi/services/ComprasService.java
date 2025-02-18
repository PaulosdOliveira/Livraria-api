package io.github.paulosdoliveira.livrariaapi.services;


import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroCartaoDTO;
import io.github.paulosdoliveira.livrariaapi.dto.livro.compras.ComprasDTO;
import io.github.paulosdoliveira.livrariaapi.mappers.LivroMapper;
import io.github.paulosdoliveira.livrariaapi.model.Compras;
import io.github.paulosdoliveira.livrariaapi.repositories.ComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ComprasService {

    @Autowired
    private ComprasRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LivroService livroService;

    @Autowired
    private LivroMapper livroMapper;

    @Transactional
    public void comprar(ComprasDTO dados) {
        var idUsuario = dados.idUsuario();
        var idLivro = dados.idLivro();
        var usuario = usuarioService.buscarPorId(idUsuario);
        var livro = livroService.buscarPorId(idLivro);
        var compra = new Compras(usuario, livro);
        repository.save(compra);
    }

    public List<LivroCartaoDTO> getLivrosComprados(UUID id) {
        var resultado = repository.getLivrosComprados(id);
        var lista = resultado.stream().map(livroMapper::toCartao).toList();
        return lista;
    }
}
