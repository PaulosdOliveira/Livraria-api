package io.github.paulosdoliveira.livrariaapi.services;

import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroCadastroDTO;
import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroCartaoDTO;
import io.github.paulosdoliveira.livrariaapi.mappers.LivroMapper;
import io.github.paulosdoliveira.livrariaapi.model.Livro;
import io.github.paulosdoliveira.livrariaapi.model.enums.GeneroLivro;
import io.github.paulosdoliveira.livrariaapi.repositories.LivroRepository;
import io.github.paulosdoliveira.livrariaapi.validations.LivroValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.UUID;


@Service
public class LivroService {


    @Autowired
    private LivroMapper mapper;

    @Autowired
    private  LivroRepository repository;

    @Autowired
    private LivroValidator validator;


    //Salvar novo livro
    public void salvarLivro(LivroCadastroDTO dto) {
        var autor = validator.validar(dto);
        var livro = mapper.toEntity(dto, autor);
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


    public  void deletarEmCascata(UUID idAutor) {
        repository.deletarEmCascata(idAutor);
    }


    public Page<LivroCartaoDTO> buscaComFiltro(String titulo, GeneroLivro genero, Integer ano, boolean maisAntigo) {
        var consulta = repository.buscaFiltrada(titulo, genero, ano, maisAntigo);
        var listaDTO = consulta.stream().map(mapper::toCartao).toList();
        Pageable page = PageRequest.of(0, 12);
        Page<LivroCartaoDTO> pagina = new PageImpl<>(listaDTO, page, listaDTO.size());
        return pagina;
    }

    public boolean existsByIsbn(String isbn) {
        return repository.existsByISBN(isbn);
    }

    public boolean existsByTitulo(String titulo) {
        return repository.existsByISBN(titulo);
    }

    public Livro buscarPorId(UUID id) {
        return repository.findById(id).orElse(null);
    }
}
