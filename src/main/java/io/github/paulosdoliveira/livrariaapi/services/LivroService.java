package io.github.paulosdoliveira.livrariaapi.services;

import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroCadastroDTO;
import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroCartaoDTO;
import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroNovosDadosDTO;
import io.github.paulosdoliveira.livrariaapi.mappers.LivroMapper;
import io.github.paulosdoliveira.livrariaapi.model.Livro;
import io.github.paulosdoliveira.livrariaapi.model.enums.GeneroLivro;
import io.github.paulosdoliveira.livrariaapi.repositories.LivroRepository;
import io.github.paulosdoliveira.livrariaapi.validations.LivroValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;


@Service
public class LivroService {


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
        if (dados.getGenero()  != null) livro.setGenero(dados.getGenero());
        if (StringUtils.hasText(dados.getTitulo())) livro.setTitulo(dados.getTitulo());
        if (dados.getDataPublicacao() != null) livro.setDataPublicacao(dados.getDataPublicacao());
        if(StringUtils.hasText(dados.getISBN())) livro.setISBN(dados.getISBN());
        if (autor != null) livro.setAutor(autor);
    }

    public void deletarEmCascata(UUID idAutor) {
        repository.deletarEmCascata(idAutor);
    }

    public Page<LivroCartaoDTO> buscaComFiltro(String titulo, GeneroLivro genero, Integer ano, boolean maisAntigo) {
        var consulta = repository.buscaFiltrada(titulo, genero, ano, maisAntigo);
        var listaDTO = consulta.stream().map(mapper::toCartao).toList();
        Pageable page = PageRequest.of(0, 12);
        Page<LivroCartaoDTO> pagina = new PageImpl<>(listaDTO, page, listaDTO.size());
        return pagina;
    }

    public Livro buscarPorId(UUID id) {
        return repository.findById(id).orElse(null);
    }
}
