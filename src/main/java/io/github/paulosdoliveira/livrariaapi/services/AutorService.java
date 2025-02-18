package io.github.paulosdoliveira.livrariaapi.services;

import io.github.paulosdoliveira.livrariaapi.dto.livro.autor.AutorDTO;
import io.github.paulosdoliveira.livrariaapi.mappers.AutorMapper;
import io.github.paulosdoliveira.livrariaapi.model.Autor;
import io.github.paulosdoliveira.livrariaapi.model.Foto;
import io.github.paulosdoliveira.livrariaapi.repositories.AutorRepository;
import io.github.paulosdoliveira.livrariaapi.validations.AutorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    @Autowired
    private FotoService fotoService;

    @Autowired
    private AutorMapper mapper;

    @Autowired
    private AutorValidator validator;

    private static String URLFOTO = "http://localhost:8080/autores/foto?id=";

    @Transactional
    public void cadastraAutor(AutorDTO dados, MultipartFile arquivo) {
        validator.validar(dados);
        var autor = mapper.toEntity(dados);
        var autorSalvo = repository.save(autor);
        if (arquivo == null) {
            autorSalvo.setUrlFoto("http://localhost:8080/autores/foto?id=1d80a3d0-e09b-415b-a211-d068bf419620");
        } else {
            try {
                fotoService.salvarFoto(arquivo, autorSalvo);
                String urlFoto = URLFOTO + autorSalvo.getId();
                autorSalvo.setUrlFoto(urlFoto);
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }


    public void deletarAutor(UUID id) {
        var possivelAutor = repository.findById(id)
                .orElse(null);
        repository.delete(possivelAutor);

    }

    public List<Autor> consultaFiltrada(String nome, Integer ano) {
        return repository.buscaFiltrada(nome, ano);
    }

    public List<Autor> buscaAleatoria() {
        return repository.findAll();
    }

    @Transactional
    public Autor buscarPorId(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public Foto buscarFoto(Autor autor) {
        return fotoService.buscarFoto(autor);
    }

    public void mudarFoto(MultipartFile arquivo, UUID idFoto) throws IOException {
        fotoService.mudarFoto(arquivo, idFoto);
    }
}
