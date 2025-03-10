package io.github.paulosdoliveira.livrariaapi.services;

import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroCadastroDTO;
import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroCartaoDTO;
import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroNovosDadosDTO;
import io.github.paulosdoliveira.livrariaapi.dto.livro.PageLivroDTO;
import io.github.paulosdoliveira.livrariaapi.mappers.LivroMapper;
import io.github.paulosdoliveira.livrariaapi.model.Livro;
import io.github.paulosdoliveira.livrariaapi.model.Usuarios;
import io.github.paulosdoliveira.livrariaapi.repositories.LivroRepository;
import io.github.paulosdoliveira.livrariaapi.validations.LivroValidator;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
public class LivroService {


    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LivroMapper mapper;

    @Autowired
    private LivroRepository repository;

    @Autowired
    private LivroValidator validator;


    //Salvar novo livro
    public void salvarLivro(LivroCadastroDTO dto) {
        var livro = mapper.toEntity(dto);
        validator.validar(livro, dto.getIdAutor());
        repository.save(livro);
    }

    // Ocultar livro setando ativo para falso
    @Transactional
    public void deletarLivro(UUID id) {
        var possivelLivro = repository.findById(id);
        if (possivelLivro.isPresent()) {
            var livro = possivelLivro.get();
            livro.setAtivo(false);
        }
    }

    @Transactional
    public void alterarInformacoes(LivroNovosDadosDTO dados, UUID idLivro) {
        var livro = repository.findById(idLivro).get();
        var autor = validator.validar(livro, dados.getIdAutor());
        if (StringUtils.hasText(dados.getDescricao())) livro.setDescricao(dados.getDescricao());
        if (dados.getGenero() != null) livro.setGenero(dados.getGenero());
        if (StringUtils.hasText(dados.getTitulo())) livro.setTitulo(dados.getTitulo());
        if (dados.getDataPublicacao() != null) livro.setDataPublicacao(dados.getDataPublicacao());
        if (StringUtils.hasText(dados.getISBN())) livro.setISBN(dados.getISBN());
        if(dados.getPreco() != null) livro.setPreco(dados.getPreco());
        if (dados.getImagem() != null) livro.setImagem(dados.getImagem());
        if (autor != null) livro.setAutor(autor);
    }

    public PageLivroDTO buscaComFiltro(String titulo, String genero, Integer ano, boolean maisAntigo, int numPagina) {
        var consulta = repository.buscaFiltrada(titulo, genero, ano, maisAntigo, numPagina);
        String idUsuarioLogado = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        Usuarios usuarioLogado = usuarioService.buscarPorId(UUID.fromString(idUsuarioLogado));
        var listaDTO = consulta.stream().map( livro -> mapper.toCartao(livro, usuarioLogado)).toList();

        //Lista final
        List<LivroCartaoDTO> listaFinal  = new ArrayList<>();
        int QUANTIDADE_ELEMENTOS = 15;
        int inicio = numPagina * QUANTIDADE_ELEMENTOS;
        int meta = (numPagina +  1 ) * QUANTIDADE_ELEMENTOS;
        for(int i = inicio; i < meta && i < listaDTO.size(); i ++){
            System.out.println(i);
            listaFinal.add(listaDTO.get(i));
        }
        int suporte = listaDTO.size() / QUANTIDADE_ELEMENTOS + 1;
        short qtdPagina = (short) suporte;
        var paginaDTO = new PageLivroDTO(listaFinal,qtdPagina);
        return paginaDTO;
    }

    public List<LivroCartaoDTO> buscarSessaoGenero(String genero){
        var consulta = repository.buscarSessaoGenero(genero);
        String idUsuarioLogado = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        Usuarios usuarioLogado = usuarioService.buscarPorId(UUID.fromString(idUsuarioLogado));
        var listaDTO = consulta.stream().map( livro -> mapper.toCartao(livro, usuarioLogado)).toList();
        return listaDTO;
    }

    public Livro buscarPorTitulo(String titulo){
        return repository.findByTitulo(titulo).orElse(null);
    }

    public void deletarEmCascata(UUID idAutor) {
        repository.deletarEmCascata(idAutor);
    }

    public Livro buscarPorId(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public byte[] buscarImagem(UUID idLivro) {
        return repository.buscarImagem(idLivro);
    }
}
