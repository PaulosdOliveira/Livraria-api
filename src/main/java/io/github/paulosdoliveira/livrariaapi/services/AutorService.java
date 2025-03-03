package io.github.paulosdoliveira.livrariaapi.services;

import io.github.paulosdoliveira.livrariaapi.dto.livro.autor.AutorDTO;
import io.github.paulosdoliveira.livrariaapi.dto.livro.autor.AutorOptionDTO;
import io.github.paulosdoliveira.livrariaapi.mappers.AutorMapper;
import io.github.paulosdoliveira.livrariaapi.model.Autor;
import io.github.paulosdoliveira.livrariaapi.model.Foto;
import io.github.paulosdoliveira.livrariaapi.repositories.AutorRepository;
import io.github.paulosdoliveira.livrariaapi.validations.AutorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
        var autor = mapper.toEntity(dados);
        validator.validar(autor);
        var autorSalvo = repository.save(autor);
        if (arquivo == null) {
            autorSalvo.setUrlFoto(URLFOTOPADRAO);
        } else {
            try {
                salvarFoto(arquivo, autor);
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
    public void editarInformacoes(String nome, String breveDescricao, LocalDate dataNascimento, MultipartFile arquivo, UUID idAutor) throws IOException {
        var autor = new Autor(idAutor, nome, dataNascimento);
        validator.validar(autor);
        autor = buscarAutorAtivoPorId(idAutor);
        if (StringUtils.hasText(nome)) autor.setNome(nome);
        if (StringUtils.hasText(breveDescricao)) autor.setBreveDescricao(breveDescricao);
        if (dataNascimento != null) autor.setDataNascimento(dataNascimento);
        if (arquivo != null) salvarFoto(arquivo, autor);
    }

    public List<Autor> consultaFiltrada(String nome, Integer ano) {
        return repository.buscaFiltrada(nome, ano);
    }

    public List<AutorDTO> buscaLetrada(Character letra) {
        if (letra != null) {
            var resultado = repository.buscaLetrada(letra.toString().toUpperCase());
            List<AutorDTO> lista = resultado.stream()
                    .map(mapper::toDTO).toList();
            return lista;
        }
        return null;
    }

    @Transactional
    public Autor buscarAutorAtivoPorId(UUID id) {
        return repository.findByIdAndAtivo(id, true).orElse(null);
    }

    public Foto buscarFoto(UUID idFoto) {
        return fotoService.buscarFoto(idFoto);
    }


    //Salvar ou alterar foto de autor
    private void salvarFoto(MultipartFile arquivo, Autor autor) throws IOException {
        var idFotoSalva = fotoService.buscarIdFoto(autor.getId());
        var idNovaFoto = fotoService.salvarFoto(arquivo, autor, idFotoSalva);
        if (idFotoSalva != null) {
            autor.setUrlFoto(URLBASEFOTO + idNovaFoto);
        }

    }


    public List<AutorOptionDTO> criarOptionAutor(){
        var listaNomes = repository.nomesAutores();
        var listaId = repository.idAutores();
        List<AutorOptionDTO> dados = new ArrayList<>();
        for(int i = 0; i < listaNomes.size(); i++){
            String nome  = listaNomes.get(i);
            UUID id = listaId.get(i);
            dados.add(new AutorOptionDTO(nome, id));
        }
        return dados;
    }

}
