package io.github.paulosdoliveira.livrariaapi.services;

import io.github.paulosdoliveira.livrariaapi.dto.livro.autor.AutorDTO;
import io.github.paulosdoliveira.livrariaapi.mappers.AutorMapper;
import io.github.paulosdoliveira.livrariaapi.model.Autor;
import io.github.paulosdoliveira.livrariaapi.model.Foto;
import io.github.paulosdoliveira.livrariaapi.repositories.AutorRepository;
import io.github.paulosdoliveira.livrariaapi.validations.AutorValidator;
import org.springframework.beans.factory.annotation.Autowired;
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


    private String URLBASEFOTO = "http://localhost:8080/autores/foto?id=";
    private String URLFOTOPADRAO = URLBASEFOTO + "1d80a3d0-e09b-415b-a211-d068bf419620";

    @Transactional
    public void cadastraAutor(AutorDTO dados, MultipartFile arquivo) {
        validator.validar(dados);
        var autor = mapper.toEntity(dados);
        var autorSalvo = repository.save(autor);
        if (arquivo == null) {
            autorSalvo.setUrlFoto(URLFOTOPADRAO);
        } else {
            try {
                fotoService.salvarFoto(arquivo, autorSalvo);
                String urlFoto = URLBASEFOTO + autorSalvo.getId();
                autorSalvo.setUrlFoto(urlFoto);
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }

    @Transactional
    public boolean deletarAutor(UUID id) {
        var possivelAutor = repository.findById(id)
                .orElse(null);
        if (possivelAutor != null) {
            possivelAutor.setAtivo(false);
            return true;
        }
        return false;
    }

    @Transactional
    public void mudarFoto(MultipartFile arquivo, UUID idAutor) throws IOException {
        var autor = buscarAutorAtivoPorId(idAutor);
        if (autor != null) {
            String urlFoto = autor.getUrlFoto();
            if (urlFoto.equals(URLFOTOPADRAO)) {
                System.out.println("/**********************************************************");
                fotoService.salvarFoto(arquivo, autor);
                autor.setUrlFoto(URLBASEFOTO + autor.getId());
            } else {
                System.out.println("/---------------------------------------------------------");
                Foto fotoAtual = fotoService.buscarFoto(idAutor);
                fotoService.mudarFotoAtual(arquivo, fotoAtual);
            }
        }


    }

    public List<Autor> consultaFiltrada(String nome, Integer ano) {
        return repository.buscaFiltrada(nome, ano);
    }

    public List<Autor> buscaAleatoria() {
        return repository.findAll();
    }

    @Transactional
    public Autor buscarAutorAtivoPorId(UUID id) {
        return repository.findByIdAndAtivo(id, true).orElse(null);
    }

    public Foto buscarFoto(UUID idAutor) {
        return fotoService.buscarFoto(idAutor);
    }


}
